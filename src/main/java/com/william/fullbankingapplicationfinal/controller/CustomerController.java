package com.william.fullbankingapplicationfinal.controller;

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
import java.net.URI;
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
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long id){
        if(customerService.existsById(id)) {
            customerService.updateCustomer(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return createCustomer(customer);
        }
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Customer>> showAllCustomers() {
        Iterable<Customer> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findCustomerById(@PathVariable Long id) {
        Iterable<Customer> customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}/bills", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Bill>> getBillsByCustomer(@PathVariable Long id) {
        Iterable<Bill> allBills = customerService.getBillsByCustomer(id);
        Iterable<Account>
        return new ResponseEntity<>(allBills, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}/accounts", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Account>> getAccountsByCustomer(@PathVariable Long id) {
        Iterable<Account> allAccounts = customerService.getAccountsByCustomer(id);
        return new ResponseEntity<>(allAccounts, HttpStatus.OK);
    }

}
