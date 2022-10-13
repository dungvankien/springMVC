package com.bank.service.customer;

import com.bank.model.Customer;
import com.bank.service.IGeneralService;

import java.math.BigDecimal;

public interface ICustomerService extends IGeneralService<Customer> {
Iterable<Customer>findAllByDeletedIsFalse();

void incrementBalance(BigDecimal balance, Long id);

void reduceBalance (BigDecimal balance, Long id);

Iterable<Customer> findAllByDeletedIsFalseAndByIdWithOutSender(Long id);
}
