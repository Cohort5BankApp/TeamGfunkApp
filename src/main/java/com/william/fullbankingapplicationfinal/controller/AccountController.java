package com.william.fullbankingapplicationfinal.controller;


import com.william.fullbankingapplicationfinal.error.HttpException;
import com.william.fullbankingapplicationfinal.model.*;
import com.william.fullbankingapplicationfinal.service.AccountService;
import com.william.fullbankingapplicationfinal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(value= "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Iterable<Account> getAllAccounts(){
        ArrayList<Account> accounts = accountService.getAllAccounts();
        if(accounts.size() < 1)
            throw new HttpException(HttpStatus.NOT_FOUND, "error fetching accounts");
        if(accounts.size() > 0)
            throw new HttpException(HttpStatus.OK, "success");
        return accounts;

    }


    @GetMapping(value = "/{accountId}/customer")
    public Customer getAccountOwner(@PathVariable("accountId") Long account_id){
        Optional<Account> account = accountService.getAccountById(account_id);
        Customer customer = accountService.getAccountOwner(account_id);
        if(customer == null)
            throw new HttpException(HttpStatus.NOT_FOUND, "error fetching customer");
        if(customer != null)
            throw new HttpException(HttpStatus.OK, "success");

        return customer;

    }

    @PostMapping(value = "/createAccount")
    public Optional<Account> createAccount (Long customer_id, @RequestBody Account account) {
        accountService.createAccount(customer_id, account);
        Optional<Account> target_account = accountService.getAccountById(account.getId());
        if(!target_account.isPresent())
            throw new HttpException(HttpStatus.NOT_FOUND, "error creating account");
            if (target_account.isPresent())

            throw new HttpException(HttpStatus.CREATED, "success");
        return target_account;
    }


    @GetMapping(value = "/{accountId}")
    public Optional<Account> getAccountById(@PathVariable("accountId") Long account_id){
        Optional<Account> account = accountService.getAccountById(account_id);
        if(!account.isPresent())
            throw new HttpException(HttpStatus.NOT_FOUND, "error fetching account");
        if(account.isPresent())
            throw new HttpException(HttpStatus.OK, "success");

        return account;
    }

    @PutMapping(value = "/{accountId}")
    public Optional<Account> updateAccount(@PathVariable("accountId") Long account_id, @RequestBody Account account){
        accountService.updateAccount(account);
        Optional<Account> accountOptional = accountService.getAccountById(account_id);
        if(!accountOptional.isPresent())
            throw new HttpException(HttpStatus.NOT_FOUND, "error updating account");
        if(accountOptional.isPresent())
            throw new HttpException(HttpStatus.OK, "success");

        return accountOptional;
    }

    @DeleteMapping(value = "/{accountId}")
    public void deleteAccount(@PathVariable("accountId") Long account_id){
        accountService.deleteAccount(account_id);
        Optional<Account> accountOptional = accountService.getAccountById(account_id);
        if(!accountOptional.isPresent())
            throw new HttpException(HttpStatus.OK, "successfully deleted account");
        if(accountOptional.isPresent())
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "error deleting account");
    }
    @GetMapping(value = "/{accountId}/deposits")
    public Iterable<Deposit> getDepositsByAccount(@PathVariable("accountId") Long account_id){
        ArrayList<Deposit> deposits = accountService.getDepositsByAccount(account_id);
        if(deposits.size() < 1)
            throw new HttpException(HttpStatus.NOT_FOUND, "error fetching deposits");
        if(deposits.size() > 0)
            throw new HttpException(HttpStatus.OK, "success");

        return deposits;

    }

    @GetMapping(value= "/{accountId}/withdrawals")
    public Iterable<Withdrawal> getWithdrawlsByAccount(@PathVariable("accountId") Long account_id){
        Account account = accountService.getAccountById(account_id).get();
        ArrayList<Withdrawal> withdrawls =  accountService.getWithdrawlsByAccount(account_id);
        if(withdrawls.size() < 1)
            throw new HttpException(HttpStatus.NOT_FOUND, "error fetching withdrawls");
        if(withdrawls.size() > 0)
            throw new HttpException(HttpStatus.OK, "success");

        return withdrawls;
    }
    @GetMapping(value = "/{accountId}/bills")
    public Iterable<Bill> getBillsByAccount(@PathVariable("customerId") Long customer_id, @PathVariable("accountId") Long account_id){
        ArrayList<Bill> bills = accountService.getBillsByAccount(account_id);
        if(bills.size() < 1)
            throw new HttpException(HttpStatus.NOT_FOUND, "error fetching bills");
        if(bills.size() > 0)
            throw new HttpException(HttpStatus.OK, "success");

        return bills;
    }

}
