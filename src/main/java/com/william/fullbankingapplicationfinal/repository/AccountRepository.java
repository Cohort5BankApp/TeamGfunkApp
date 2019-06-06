package com.william.fullbankingapplicationfinal.repository;

import com.william.fullbankingapplicationfinal.model.Account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface AccountRepository extends CrudRepository<Account, Long> {
}
