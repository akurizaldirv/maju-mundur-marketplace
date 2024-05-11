package org.majumundur.shop.service.implementation;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.majumundur.shop.model.entity.Merchant;
import org.majumundur.shop.model.entity.UserCredential;
import org.majumundur.shop.model.request.auth.customer.RegisterCustomerRequest;
import org.majumundur.shop.model.request.auth.merchant.RegisterMerchantRequest;
import org.majumundur.shop.model.response.merchant.MerchantResponse;
import org.majumundur.shop.model.response.merchant.SimpleMerchantResponse;
import org.majumundur.shop.repository.MerchantRepository;
import org.majumundur.shop.service.MerchantService;
import org.majumundur.shop.util.exception.DataNotFoundException;
import org.majumundur.shop.util.mapper.MerchantMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository repository;

    @Override
    public Merchant create(UserCredential userCredential, RegisterMerchantRequest request) {
        throwIfNotValidated(request);
        return repository.save(MerchantMapper.mapToEntity(request, userCredential));
    }

    @Override
    public Merchant getMerchantById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Merchant ID not found")
        );
    }

    @Override
    public Merchant getMerchantByUserId(Integer id) {
        return repository.findByUserCredential_Id(id).orElseThrow(
                () -> new DataNotFoundException("Merchant not found")
        );
    }

    @Override
    public MerchantResponse getById(Integer id) {
        return MerchantMapper.mapToRes(this.getMerchantById(id));
    }

    @Override
    public SimpleMerchantResponse getSimpleById(Integer id) {
        return MerchantMapper.mapToSimpleRes(this.getMerchantById(id));
    }

    @Override
    public List<MerchantResponse> getAll() {
        List<Merchant> merchants = repository.findAll();
        return merchants.stream().map(MerchantMapper::mapToRes).toList();
    }

    @Override
    public void delete(Integer id) {
        this.throwIfIdNotExist(id);
        repository.deleteById(id);
    }

    @Override
    public void throwIfIdNotExist(Integer id) {
        this.getMerchantById(id);
    }

    @Override
    public void throwIfNotValidated(RegisterMerchantRequest request) {
        long validatePhone = repository.countByPhone(request.getPhone());
        if (validatePhone > 0) {
            throw new ValidationException("Phone already registered");
        }

        long validateEmail = repository.countByEmail(request.getEmail());
        if (validateEmail > 0) {
            throw new ValidationException("Email already registered");
        }
    }
}
