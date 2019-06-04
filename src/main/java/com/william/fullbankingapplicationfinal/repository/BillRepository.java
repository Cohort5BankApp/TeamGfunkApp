package com.william.fullbankingapplicationfinal.repository;

import com.william.fullbankingapplicationfinal.model.Bill;
import com.william.fullbankingapplicationfinal.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {
}
