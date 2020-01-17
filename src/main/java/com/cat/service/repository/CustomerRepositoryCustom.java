package com.cat.service.repository;

import com.cat.service.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepositoryCustom extends CrudRepository<Customer, Long> {

    Optional<Customer> findCustomerByCustomerNameAndCustomerSecondName(String name, String ownerName);

    Optional<Customer> findCustomerByCustomerPhoneNumber(Long phoneNumber);
}
