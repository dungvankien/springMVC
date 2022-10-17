package com.bank.controller.rest;

import com.bank.model.Customer;
import com.bank.model.Deposit;
import com.bank.model.dto.CustomerCreateDTO;
import com.bank.model.dto.CustomerDTO;
import com.bank.model.dto.CustomerEditDTO;
import com.bank.model.dto.DepositDTO;
import com.bank.service.customer.ICustomerService;
import com.bank.service.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;


@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDepositService depositService;
    @GetMapping
    public ResponseEntity<Iterable<Customer>> getAll(){
        return new ResponseEntity<>((customerService.findAllByDeletedIsFalse()), HttpStatus.OK);
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getById(@PathVariable Long customerId) {
        Optional<Customer> customerOptional = customerService.findById(customerId);
        if(!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = customerOptional.get();
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<CustomerCreateDTO> create(@RequestBody CustomerCreateDTO customerDTO){
        customerDTO.setId(0L);
        Customer customer = customerDTO.toCustomer();
        Customer newCustomer = customerService.save(customer);
        customerDTO.setId(newCustomer.getId());
        customerDTO.setBalance("0");
        return new ResponseEntity<>(newCustomer.toCustomerCreateDTO(),HttpStatus.CREATED);
    }
    @PostMapping("/edit")
    public ResponseEntity<CustomerEditDTO> update (@RequestBody CustomerEditDTO customerDTO){
            Optional<Customer> customerOptional = customerService.findById(customerDTO.getId());
            if(!customerOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Customer customer = customerDTO.toCustomer();
            Customer newCustomer = customerService.save(customer);
            return new ResponseEntity<>(newCustomer.toCustomerEditDTO(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/deposit")
    public ResponseEntity<CustomerDTO> depositAmount(@RequestBody DepositDTO depositDTO){
        Long customerId = depositDTO.getCustomerId();
        Optional<Customer> customerOptional = customerService.findById(customerId);
        if(!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Deposit deposit = new Deposit();
        Customer customer = customerOptional.get();
        BigDecimal transactionAmount = new BigDecimal(depositDTO.getTransactionAmount());
        deposit.setTransactionAmount(transactionAmount);
        deposit.setCustomer(customer);
        customerService.incrementBalance(transactionAmount, customerId);
        depositService.save(deposit);
        Optional<Customer> newCustomer = customerService.findById(customerId);
        return new ResponseEntity<>(newCustomer.get().toCustomerDTO(),HttpStatus.CREATED);
    }
}
