package com.cat.service.repository;

import com.cat.service.entity.Customer;

public interface CustomerRepositoryCustom {

    Customer findCustomerByCustomerNameAndCustomerSecondName(String name, String ownerName);
}
