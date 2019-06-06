package com.william.fullbankingapplicationfinal.repository;

import com.william.fullbankingapplicationfinal.model.Withdrawal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface WithdrawlRepository extends CrudRepository<Withdrawal, Long> {
}
