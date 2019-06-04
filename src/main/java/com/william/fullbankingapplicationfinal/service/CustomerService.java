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

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    BillRepository billRepository;
    AccountRepository accountRepository;

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customer_id) {
        return customerRepository.findById(customer_id).get();
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public ArrayList<Bill> getBillsByCustomer(Long customer_id) {
        Customer customer = getCustomerById(customer_id);
        Iterable<Bill> bills = billRepository.findAll();
        ArrayList<Bill> customer_bills = new ArrayList<>();
        for(Bill bill : bills){
            if(bill.getAccount_id() == customer.getCustomer_id()) {
                customer_bills.add(bill);
            }
        }
        return customer_bills;
    }

    public ArrayList<Account> getAccountsByCustomer(Long customer_id) {
        Customer customer = getCustomerById(customer_id);
        Iterable<Account> accounts = accountRepository.findAll();
        ArrayList<Account> customer_accounts = new ArrayList<>();
        for(Account account : accounts) {
            if(account.getId() == customer.getCustomer_id()) {
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
