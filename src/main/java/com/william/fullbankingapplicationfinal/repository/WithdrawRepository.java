package com.william.fullbankingapplicationfinal.repository;


import com.william.fullbankingapplicationfinal.model.Withdrawal;
import org.springframework.data.repository.CrudRepository;

public interface WithdrawRepository extends CrudRepository<Withdrawal,Long> {
}
