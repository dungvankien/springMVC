package com.example.customermanagementstoredprocedure.service;

import com.example.customermanagementstoredprocedure.model.Customer;
import com.example.customermanagementstoredprocedure.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public boolean insertWithStoredProcedure(Customer customer) {
        return customerRepository.insertWithStoredProcedure(customer);
    }
}
