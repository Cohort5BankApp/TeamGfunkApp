package com.william.fullbankingapplicationfinal.repository;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Customer, Long> {
}
