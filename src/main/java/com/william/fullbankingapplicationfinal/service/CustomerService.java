package com.william.fullbankingapplicationfinal.service;

import com.william.fullbankingapplicationfinal.model.Account;
import com.william.fullbankingapplicationfinal.model.Bill;
import com.william.fullbankingapplicationfinal.model.Customer;
import com.william.fullbankingapplicationfinal.repository.AccountRepository;
import com.william.fullbankingapplicationfinal.repository.BillRepository;
import com.william.fullbankingapplicationfinal.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private AccountRepository accountRepository;

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public ArrayList<Customer> getAllCustomers() {
        Iterable<Customer> customers = customerRepository.findAll();
        ArrayList<Customer> customer_list = new ArrayList<>();
        for(Customer customer : customers) {
            customer_list.add(customer);
        }
        return customer_list;
    }

    public Optional<Customer> getCustomerById(Long customer_id) {
        Optional<Customer> customer = customerRepository.findById(customer_id);
        return customer;
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public ArrayList<Bill> getBillsByCustomer(Long customer_id) {
        Iterable<Bill> bills = billRepository.findAll();
        Iterable<Account> accounts = accountRepository.findAll();
        ArrayList<Bill> customer_bills = new ArrayList<>();
        for(Bill bill : bills){
            if(bill.getAccount_id() == customer_id) {
                customer_bills.add(bill);
            }
        }
        return customer_bills;
    }

    //customer:1 account:1
    //customer: 1 account2: 2


    public ArrayList<Account> getAccountsByCustomer(Long customer_id) {
        Optional<Customer> customer = getCustomerById(customer_id);
        Iterable<Account> accounts = accountRepository.findAll();
        ArrayList<Account> customer_accounts = new ArrayList<>();
        for(Account account : accounts) {
            if(account.getCustomer().getCustomer_id() == customer_id) {
                customer_accounts.add(account);
            }
        }
        return customer_accounts;
    }

    public boolean existsById(Long id) {
        if(customerRepository.existsById(id)){
            return true;
        } else {
            return false;
        }
    }

}
