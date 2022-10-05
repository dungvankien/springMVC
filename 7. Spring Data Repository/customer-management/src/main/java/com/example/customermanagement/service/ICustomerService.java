package com.example.customermanagement.service;


import com.example.customermanagement.model.Customer;
import com.example.customermanagement.model.Province;

public interface ICustomerService extends IGeneralService<Customer> {
    Iterable<Customer> findAllByProvince(Province province);
}