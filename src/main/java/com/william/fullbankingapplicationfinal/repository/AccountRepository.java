package com.william.fullbankingapplicationfinal.repository;

import com.william.fullbankingapplicationfinal.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
