package com.william.fullbankingapplicationfinal.repository;

import com.william.fullbankingapplicationfinal.model.Customer;
import com.william.fullbankingapplicationfinal.model.Withdrawal;
import org.springframework.data.repository.CrudRepository;

public interface WithdrawlRepository extends CrudRepository<Withdrawal, Long> {
}
