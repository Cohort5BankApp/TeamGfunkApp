package com.william.fullbankingapplicationfinal.controller;

import com.william.fullbankingapplicationfinal.model.Account;
import com.william.fullbankingapplicationfinal.model.Customer;
import com.william.fullbankingapplicationfinal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/accounts")
    public ResponseEntity<?> getAllAccounts(){
        Iterable<Account> accounts = accountService.getAllAcounts();
        if(accounts == null) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "error fetching accounts");
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping(value = ("/{customerId}/accounts"))
    public ResponseEntity<?> getAccountsByCustomer(@PathVariable Long customer_id){
        Iterable<Account> accounts = customerService.getAccountsByCustomer(customer_id);

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/accounts/{accountId}/customer")
    public ResponseEntity<?> getAccountOwner(@PathVariable("customerId") Long customer_id, @PathVariable("accountId") Long account_id){
        Customer customer = accountService.getAccountOwner(account_id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


    @PostMapping(value = "/{customerId}/accounts/createAccount")
    public ResponseEntity<?> createAccount(@PathVariable Long customer_id, @RequestBody Account account){
        accountService.createAccount(customer_id, account);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    @GetMapping(value = "/{customerId}/accounts/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable("customerId") Long customer_id, @PathVariable("accountId") Long account_id){
        Optional<Account> account = accountService.getAccountById(account_id);
        if(account == null){
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "error fetching account");

        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping(value = "/{customerId}/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable("customerId") Long customer_id, @PathVariable("accountId") Long account_id, @RequestBody Account account){
        accountService.updateAccount(account);
        if(account == null) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Error");
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable("customerId") Long customer_id, @PathVariable("accountId") Long account_id){
        accountService.deleteAccount(account_id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @GetMapping(value = "/{customerId}/accounts/{accountId}/deposits")
    public ResponseEntity<?> getDepositsByAccount(@PathVariable("customerId") Long customer_id, @PathVariable("accountId") Long account_id){
        Iterable<Deposit> deposits = accountService.getDepositsByAccount(account_id);
        return new ResponseEntity<>(deposits, HttpStatus.OK);
    }

    @GetMapping(value= "/{customerId}/accounts/{accountId}/withdrawls")
    public ResponseEntity<?> getWithdrawlsByAccount(@PathVariable("customerId") Long customer_id, @PathVariable("accountId") Long account_id){
        Iterable<Withdrawl> withdrawls =  accountService.getWithdrawlsByAccount(account_id);
        return new ResponseEntity<>(withdrawls, HttpStatus.OK);
    }
    @GetMapping(value = "/{customerId}/accounts/{accountId}/bills")
    public ResponseEntity<?> getBillsByAccount(@PathVariable("customerId") Long customer_id, @PathVariable("accountId") Long account_id){
        Iterable<Bill> bills = accountService.getBillsByAccount(account_id);
        if(bills == null) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "error fetching bills");
        }
        return new ResponseEntity(bills, HttpStatus.OK);
    }


}
