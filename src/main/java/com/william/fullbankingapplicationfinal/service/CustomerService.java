package com.william.fullbankingapplicationfinal.service;

import com.william.fullbankingapplication.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    BillRepository billRepository;

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Iterable<Bill> getBillsByCustomer(Long id) {
        return billRepository.findAll(id);
    }

    public Iterable<Account> getAccountsByCustomer(Long id) {
        return accountRepository.findAll(id);
    }

    public boolean existsById(Long id) {
        if(customerRepository.existsById(id)){
            return true;
        } else {
            return false;
        }
    }

}
