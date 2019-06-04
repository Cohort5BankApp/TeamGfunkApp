package com.william.fullbankingapplicationfinal.service;

import com.william.fullbankingapplicationfinal.model.Account;
import com.william.fullbankingapplicationfinal.model.Deposit;
import com.william.fullbankingapplicationfinal.repository.AccountRepository;
import com.william.fullbankingapplicationfinal.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Optional<Deposit> getDepositById(Long id){
        return depositRepository.findById(id);
    }

    public void createDeposit(Long account_id, Deposit deposit){
        depositRepository.save(deposit);
        deposit.setAccount_id(account_id);
        Account account = accountRepository.findById(account_id).get();
        Double newBalance = account.getBalance() + deposit.getAmount();
        account.setBalance(newBalance);


    }

    public void updateDeposit(Long account_id, Deposit deposit){
        depositRepository.save(deposit);
        Account account = accountRepository.findById(account_id).get();
        Double newBalance = account.getBalance() + deposit.getAmount();
        account.setBalance(newBalance);
    }

    public void deleteDeposit(Long account_id, Long deposit_id){
        depositRepository.deleteById(deposit_id);
        Account account = accountRepository.findById(account_id).get();
        Deposit deposit = depositRepository.findById(deposit_id).get();
        Double newBalance = account.getBalance() - deposit.getAmount();
        account.setBalance(newBalance);
    }

}
