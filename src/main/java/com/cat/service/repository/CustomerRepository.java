package com.cat.service.repository;

import com.cat.service.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>, CustomerRepositoryCustom {
}
