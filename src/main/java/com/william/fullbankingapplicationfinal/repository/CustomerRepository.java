package com.william.fullbankingapplicationfinal.repository;

import com.william.fullbankingapplicationfinal.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
