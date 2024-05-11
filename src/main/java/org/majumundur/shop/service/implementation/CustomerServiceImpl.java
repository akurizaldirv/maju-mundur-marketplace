package org.majumundur.shop.service.implementation;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.majumundur.shop.model.entity.AppUser;
import org.majumundur.shop.model.entity.Customer;
import org.majumundur.shop.model.entity.Merchant;
import org.majumundur.shop.model.entity.UserCredential;
import org.majumundur.shop.model.request.auth.customer.RegisterCustomerRequest;
import org.majumundur.shop.model.response.customer.CustomerResponse;
import org.majumundur.shop.model.response.customer.SimpleCustomerResponse;
import org.majumundur.shop.repository.CustomerRepository;
import org.majumundur.shop.service.CustomerService;
import org.majumundur.shop.service.MerchantService;
import org.majumundur.shop.util.exception.DataNotFoundException;
import org.majumundur.shop.util.mapper.CustomerMapper;
import org.majumundur.shop.util.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final MerchantService merchantService;
    private final JwtUtil jwtUtil;

    @Override
    public Customer create(UserCredential userCredential, RegisterCustomerRequest request) {
        throwIfNotValidated(request);
        return repository.save(CustomerMapper.mapToEntity(request, userCredential));
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Customer ID not found")
        );
    }

    @Override
    public CustomerResponse getById(Integer id) {
        return CustomerMapper.mapToRes(this.getCustomerById(id));
    }

    @Override
    public SimpleCustomerResponse getSimpleById(Integer id) {
        return CustomerMapper.mapToSimpleRes(this.getCustomerById(id));
    }

    @Override
    public Customer getCustomerByUserId(Integer id) {
        return repository.findByUserCredential_Id(id).orElseThrow(
                () -> new DataNotFoundException("Customer not found")
        );
    }

    @Override
    public List<SimpleCustomerResponse> getAll() {
        List<Customer> customers = repository.findAll();
        return customers.stream().map(CustomerMapper::mapToSimpleRes).toList();
    }

    @Override
    public List<CustomerResponse> getCustomerOrderListByMerchant(String authHeader) {
        Map<String, String> userInfo = jwtUtil.getUserInfoByToken(authHeader.substring(7));
        Merchant merchant = merchantService.getMerchantByUserId(Integer.parseInt(userInfo.get("userId")));

        List<Customer> customers = repository.getCustomerOrderListByMerchant(merchant.getId());
        return customers.stream().map(CustomerMapper::mapToRes).toList();
    }

    @Override
    public void updatePoint(Integer point, Integer id) {
        Customer customer = this.getCustomerById(id);
        repository.updatePoint(customer.getPointsObtained() + point, id);
    }

    @Override
    public void delete(Integer id) {
        this.throwIfIdNotExist(id);
        repository.deleteById(id);
    }

    @Override
    public void throwIfIdNotExist(Integer id) {
        this.getCustomerById(id);
    }

    @Override
    public void throwIfNotValidated(RegisterCustomerRequest request) {
        long validatePhone = repository.countByPhone(request.getPhone());
        if (validatePhone > 0) {
            throw new ValidationException("Phone already registered");
        }

        long validateEmail = repository.countByEmail(request.getEmail());
        if (validateEmail > 0) {
            throw new ValidationException("Email already registered");
        }

        long validateIdentityNumber = repository.countByIdentityNumber(request.getIdentityNumber());
        if (validateIdentityNumber > 0) {
            throw new ValidationException("Identity Number already registered");
        }
    }
}
