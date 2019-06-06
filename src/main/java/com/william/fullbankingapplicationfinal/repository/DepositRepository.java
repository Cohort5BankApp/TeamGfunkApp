package com.william.fullbankingapplicationfinal.repository;

import com.william.fullbankingapplicationfinal.model.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface DepositRepository extends CrudRepository<Deposit,Long> {
}
