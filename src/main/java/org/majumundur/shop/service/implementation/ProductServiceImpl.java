package org.majumundur.shop.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.majumundur.shop.model.entity.Merchant;
import org.majumundur.shop.model.entity.Product;
import org.majumundur.shop.model.entity.ProductPrice;
import org.majumundur.shop.model.request.product.ProductRequest;
import org.majumundur.shop.model.request.product.ProductUpdateRequest;
import org.majumundur.shop.model.response.merchant.SimpleMerchantResponse;
import org.majumundur.shop.model.response.product.ProductResponse;
import org.majumundur.shop.repository.ProductPriceRepository;
import org.majumundur.shop.repository.ProductRepository;
import org.majumundur.shop.service.MerchantService;
import org.majumundur.shop.service.ProductService;
import org.majumundur.shop.util.enums.EStatus;
import org.majumundur.shop.util.exception.DataNotFoundException;
import org.majumundur.shop.util.exception.UnauthorizedAccessException;
import org.majumundur.shop.util.mapper.MerchantMapper;
import org.majumundur.shop.util.mapper.ProductMapper;
import org.majumundur.shop.util.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductPriceRepository productPriceRepository;
    private final MerchantService merchantService;
    private final JwtUtil jwtUtil;

    @Override
    public ProductResponse create(ProductRequest request, String authHeader) {
        Map<String, String> userInfo = jwtUtil.getUserInfoByToken(authHeader.substring(7));
        Merchant merchant = merchantService.getMerchantByUserId(Integer.parseInt(userInfo.get("userId")));

        Product product = ProductMapper.mapToEntity(request);
        ProductPrice productPrice = ProductPrice.builder()
                .stock(request.getStock())
                .product(product)
                .merchant(merchant)
                .price(request.getPrice())
                .status(EStatus.ACTIVE)
                .build();
        productPriceRepository.save(productPrice);
        SimpleMerchantResponse merchantResponse = MerchantMapper.mapToSimpleRes(merchant);

        return ProductMapper.mapToRes(product, productPrice, merchantResponse);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findByIdAndStatus(id, EStatus.ACTIVE).orElseThrow(
                () -> new DataNotFoundException("Product not found")
        );
    }

    @Override
    public ProductPrice getProductPriceByProductId(Integer id) {
        return productPriceRepository.findByProduct_IdAndStatus(id, EStatus.ACTIVE).orElseThrow(
                () -> new DataNotFoundException("Product not found")
        );
    }

    @Override
    public ProductResponse getHistoryByProductPriceId(Integer id) {
        ProductPrice productPrice = productPriceRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Product not found")
        );
        SimpleMerchantResponse merchantResponse = MerchantMapper.mapToSimpleRes(productPrice.getMerchant());
        return ProductMapper.mapToRes(productPrice.getProduct(), productPrice, merchantResponse);
    }

    @Override
    public ProductResponse getById(Integer id) {
        Product product = this.getProductById(id);
        ProductPrice productPrice = this.getProductPriceByProductId(id);
        SimpleMerchantResponse merchantResponse = MerchantMapper.mapToSimpleRes(productPrice.getMerchant());
        return ProductMapper.mapToRes(product, productPrice, merchantResponse);
    }

    @Override
    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAllByStatus(EStatus.ACTIVE);
        return products.stream().map(product -> {
            return this.getById(product.getId());
        }).toList();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductResponse update(ProductUpdateRequest request, String authHeader) {
        Map<String, String> userInfo = jwtUtil.getUserInfoByToken(authHeader.substring(7));
        Merchant merchant = merchantService.getMerchantByUserId(Integer.parseInt(userInfo.get("userId")));

        Product product = this.getProductById(request.getId());
        ProductPrice productPrice = this.getProductPriceByProductId(request.getId());

        if (!Objects.equals(productPrice.getMerchant().getId(), merchant.getId())) {
            throw new UnauthorizedAccessException("Only customers who receive rewards can access");
        }

        product.setDescription(request.getDescription());
        product.setName(request.getName());

        if (!Objects.equals(productPrice.getPrice(), request.getPrice())) {
            ProductPrice newProductPrice = productPriceRepository.save(
                    ProductPrice.builder()
                            .price(request.getPrice())
                            .status(EStatus.ACTIVE)
                            .product(product)
                            .stock(productPrice.getStock())
                            .merchant(productPrice.getMerchant())
                            .build()
            );
            productPriceRepository.updateStatus(EStatus.INACTIVE, productPrice.getId());
            product.getProductPrices().add(newProductPrice);
            productPrice = newProductPrice;
        }

        productPrice.setStock(request.getStock());
        productPriceRepository.save(productPrice);
        productRepository.save(product);
        SimpleMerchantResponse merchantResponse = MerchantMapper.mapToSimpleRes(productPrice.getMerchant());
        return ProductMapper.mapToRes(product, productPrice, merchantResponse);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateStock(Integer id, Integer stock) {
        ProductPrice productPrice = this.getProductPriceByProductId(id);
        productPriceRepository.updateStock(stock, productPrice.getId());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(Integer id, String authHeader) {
        this.throwIfIdNotExist(id);

        Map<String, String> userInfo = jwtUtil.getUserInfoByToken(authHeader.substring(7));
        Merchant merchant = merchantService.getMerchantByUserId(Integer.parseInt(userInfo.get("userId")));
        ProductPrice productPrice = this.getProductPriceByProductId(id);

        if (!Objects.equals(productPrice.getMerchant().getId(), merchant.getId())) {
            throw new UnauthorizedAccessException("Only the merchant of the product can delete it");
        }

        productRepository.updateStatus(EStatus.INACTIVE, id);
        productPriceRepository.updateStatus(EStatus.INACTIVE, productPrice.getId());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void throwIfIdNotExist(Integer id) {
        this.getProductById(id);
    }
}
