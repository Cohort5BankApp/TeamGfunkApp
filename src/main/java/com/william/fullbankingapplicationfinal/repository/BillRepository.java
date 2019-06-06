package com.william.fullbankingapplicationfinal.repository;

import com.william.fullbankingapplicationfinal.model.Bill;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface BillRepository extends CrudRepository<Bill, Long> {
}
