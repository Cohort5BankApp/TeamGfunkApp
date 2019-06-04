package com.william.fullbankingapplicationfinal.service;

import com.william.fullbankingapplicationfinal.model.Account;
import com.william.fullbankingapplicationfinal.model.Bill;
import com.william.fullbankingapplicationfinal.model.Customer;
import com.william.fullbankingapplicationfinal.repository.AccountRepository;
import com.william.fullbankingapplicationfinal.repository.BillRepository;
import com.william.fullbankingapplicationfinal.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Iterable<Customer> getCustomerById(Long customer_id) {
        return customerRepository.findById(customer_id).get();
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Iterable<Bill> getBillsByCustomer(Long customer_id) {
        return customerRepository.findAllById(customer_id).get();
    }

    public Iterable<Account> getAccountsByCustomer(Long customer_id) {
        Customer customer = getCustomerById(customer_id);
        return accountRepository.findAll(customer);
    }

    public boolean existsById(Long id) {
        if(customerRepository.existsById(id)){
            return true;
        } else {
            return false;
        }
    }

}
