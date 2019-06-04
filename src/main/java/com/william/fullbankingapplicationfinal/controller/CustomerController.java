package com.william.fullbankingapplicationfinal.controller;

import com.william.fullbankingapplicationfinal.error.HttpException;
import com.william.fullbankingapplicationfinal.model.Account;
import com.william.fullbankingapplicationfinal.model.Bill;
import com.william.fullbankingapplicationfinal.model.Customer;
import com.william.fullbankingapplicationfinal.service.BillService;
import com.william.fullbankingapplicationfinal.service.CustomerService;
import com.william.fullbankingapplicationfinal.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;
    BillService billService;
    AccountRepository accountRepository;

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer) {
        customerService.createCustomer(customer);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getCustomer_id())
                .toUri();
        responseHeaders.setLocation(newCustomerUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    public Optional<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Long customer_id){
        customerService.updateCustomer(customer);
        Optional<Customer> customerUpdate = customerService.getCustomerById(customer_id);
        if(!customerUpdate.isPresent())
            throw new HttpException(HttpStatus.NOT_FOUND, "Error updating customer");
        if(customerUpdate.isPresent())
            throw new HttpException(HttpStatus.OK, "Customer successfully updated");
        return customerUpdate;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public Iterable<Customer> showAllCustomers() {
        ArrayList<Customer> allCustomers = customerService.getAllCustomers();
        if(allCustomers.size() < 1)
            throw new HttpException(HttpStatus.NOT_FOUND, "Error fetching customers");
        if(allCustomers.size() > 0)
            throw new HttpException(HttpStatus.OK, "Success");
        return allCustomers;
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Optional<Customer> findCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if(!customer.isPresent())
            throw new HttpException(HttpStatus.NOT_FOUND, "Error fetching customer");
        if(customer.isPresent())
            throw new HttpException(HttpStatus.OK, "Success");
        return customer;
    }

    @RequestMapping(value = "/customer/{id}/bills", method = RequestMethod.GET)
    public Iterable<Bill> getBillsByCustomer(@PathVariable Long id) {
        ArrayList<Bill> allBills = customerService.getBillsByCustomer(id);
        if(allBills.size() < 1)
            throw new HttpException(HttpStatus.NOT_FOUND, "Error fetching bills");
        if(allBills.size() > 0)
            throw new HttpException(HttpStatus.OK, "Success");
    }

    @RequestMapping(value = "/customer/{id}/accounts", method = RequestMethod.GET)
    public Iterable<Account> getAccountsByCustomer(@PathVariable Long customer_id) {
        ArrayList<Account> allAccounts = customerService.getAccountsByCustomer(customer_id);
        if(allAccounts.size() < 1)
            throw new HttpException(HttpStatus.NOT_FOUND, "Error fetching accounts");
        if(allAccounts.size() > 0)
            throw new HttpException(HttpStatus.OK, "Success");
        return allAccounts;
    }

}
