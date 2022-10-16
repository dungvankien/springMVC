package com.bank.controller.rest;

import com.bank.model.Customer;
import com.bank.model.dto.CustomerCreateDTO;
import com.bank.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    @Autowired
    private ICustomerService customerService;
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
}
