package com.william.fullbankingapplicationfinal.repository;

import com.william.fullbankingapplicationfinal.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
