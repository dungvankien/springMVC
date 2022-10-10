package com.bank.controller;

import com.bank.model.Customer;
import com.bank.model.Deposit;
import com.bank.model.Transfer;
import com.bank.model.Withdraw;
import com.bank.service.customer.ICustomerService;
import com.bank.service.deposit.IDepositService;
import com.bank.service.transfer.ITransferService;
import com.bank.service.withdraw.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.*;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDepositService depositService;
    @Autowired
    private IWithdrawService withdrawService;
    @Autowired
    private ITransferService transferService;

    @GetMapping("/customers")
    public ModelAndView showListPage(){
        Iterable<Customer> customers = customerService.findfAll();
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
    @GetMapping("/create-customer")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer){
        customer.setBalance(new BigDecimal(0L));
        customerService.save(customer);
        ModelAndView modelAndView =new ModelAndView("customer/create");
        modelAndView.addObject("customer",new Customer());
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;
    }
    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()){
            ModelAndView modelAndView = new ModelAndView("customer/edit");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-customer/{id}")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }
    @GetMapping("/suspended-customer/{id}")
    public ModelAndView showSuspendedForm(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            ModelAndView modelAndView = new ModelAndView("customer/suspended");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/suspended-customer")
    public String suspendedCustomer(@ModelAttribute("customer") Customer customer){
        customerService.remove(customer.getId());
        return "redirect:/customers";
    }
    @GetMapping("/deposit-customer/{id}")
    public ModelAndView showDepositForm(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            ModelAndView modelAndView = new ModelAndView("customer/deposit");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/deposit-customer/{id}")
    public ModelAndView addDeposit(@PathVariable Long id,
                                   @RequestParam("transactionAmount") BigDecimal transactionAmount){
        Optional<Customer> customerOld = customerService.findById(id);
        Customer customer = customerOld.get();
        Deposit deposit = new Deposit();
        deposit.setTransactionAmount(transactionAmount);
        deposit.setCustomerId(customer.getId());
        depositService.save(deposit);
        BigDecimal totalBalance = (customer.getBalance()).add((deposit.getTransactionAmount()));
        customer.setBalance(totalBalance);
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customer/deposit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", " Deposit successfully");
        return modelAndView;
    }
    @GetMapping("/withdraw-customer/{id}")
    public ModelAndView showWithdrawForm(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            ModelAndView modelAndView = new ModelAndView("customer/withdraw");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/withdraw-customer/{id}")
    public ModelAndView saveWithdraw(@PathVariable Long id,
                                   @RequestParam("transactionAmount") BigDecimal transactionAmount){
        Optional<Customer> customerOld = customerService.findById(id);
        Customer customer = customerOld.get();
        Withdraw withdraw = new Withdraw();
        withdraw.setTransactionAmount(transactionAmount);
        withdraw.setCustomerId(customer.getId());
        withdrawService.save(withdraw);
        BigDecimal totalBalance = (customer.getBalance()).subtract((withdraw.getTransactionAmount()));
        customer.setBalance(totalBalance);
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customer/withdraw");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", " Withdraw successfully");
        return modelAndView;
    }
    @GetMapping("/transfer-customer/{id}")
    public ModelAndView showTransferForm(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
        Iterable<Customer> customers = customerService.findfAll();
        List<Customer> customerSet = new ArrayList<>();
        for (Customer item : customers) {
            if(!(item.getId()).equals(id)) {
                customerSet.add(item);
            }
        }
        if(customer.isPresent()){
            ModelAndView modelAndView = new ModelAndView("customer/transfer");
            modelAndView.addObject("customer", customer.get());
            modelAndView.addObject("customers", customerSet);
            Transfer transfer = new Transfer();
            transfer.setFees(10);
            modelAndView.addObject("transfer", transfer);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/transfer-customer/{id}")
    public ModelAndView saveTransfer(@PathVariable Long id,
                                     @ModelAttribute("transfer") Transfer transfer){
        Optional<Customer> customerOld = customerService.findById(id);
        Optional<Customer> customerRecipientOld = customerService.findById(transfer.getRecipientId());
        Customer customer = customerOld.get();
        Customer customerRecipient = customerRecipientOld.get();

        BigDecimal transferAmount = transfer.getTransferAmount();
        BigDecimal feesAmount = transferAmount.multiply(BigDecimal.valueOf(transfer.getFees())).divide(BigDecimal.valueOf(100));
        BigDecimal transactionAmount = transferAmount.add(feesAmount);

        customer.setBalance((customer.getBalance()).subtract(transactionAmount));
        customerService.save(customer);

        customerRecipient.setBalance((customerRecipient.getBalance()).add(transferAmount));
        customerService.save(customerRecipient);

        transfer.setId(0L);
        transfer.setTransactionAmount(transactionAmount);
        transfer.setSenderId(customer.getId());
        transfer.setFeesAmount(feesAmount);
        transferService.save(transfer);

        Iterable<Customer> customers = customerService.findfAll();
        List<Customer> customerSet = new ArrayList<>();
        for (Customer item : customers) {
            if(!(item.getId()).equals(id)) {
                customerSet.add(item);
            }
        }
        ModelAndView modelAndView = new ModelAndView("customer/transfer");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("customers", customerSet);
        modelAndView.addObject("transfer", transfer);
        modelAndView.addObject("message", " Transfer successfully");
        return modelAndView;
    }

    @GetMapping("/transferInformation")
    public ModelAndView showListTransferInformation(){
        Iterable<Transfer> transfers = transferService.findfAll();
        BigDecimal totalFeeAmount = BigDecimal.valueOf(0L);
        for (Transfer item : transfers) {
            totalFeeAmount = totalFeeAmount.add(item.getFeesAmount());
            Optional<Customer> customerSender = customerService.findById(item.getSenderId());
            item.setSenderName(customerSender.get().getFullName());
            Optional<Customer> customerRecipient = customerService.findById(item.getRecipientId());
            item.setRecipientName(customerRecipient.get().getFullName());
        }

        ModelAndView modelAndView = new ModelAndView("customer/transferInformation");
        modelAndView.addObject("transfers", transfers);
        modelAndView.addObject("totalFeeAmount", totalFeeAmount);
        return modelAndView;
    }
}
