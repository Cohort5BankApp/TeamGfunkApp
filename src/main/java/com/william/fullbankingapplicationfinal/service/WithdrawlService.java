package com.william.fullbankingapplicationfinal.service;


import com.william.fullbankingapplicationfinal.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WithdrawlService {

    @Autowired
    private WithdrawlRepository withdrawlRepository;

    @Autowired
    private AccountRepository accountRepository;


    public Optional<Withdrawl> getWithdrawlById(Long withdrawl_id){
        return withdrawlRepository.findById(withdrawl_id);
    }

    public void createWithdrawl(Long account_id, Withdrawl withdrawl){
        withdrawlRepository.save(withdrawl);
        withdrawl.setAccount_id(account_id);
        Account account = accountRepository.findById(account_id).get();
        Double newBalance = account.getBalance() - withdrawl.getAmount();
        account.setBalance(newBalance);
    }
    public void updateWithdrawl(Long account_id, Withdrawl withdrawl){
        withdrawlRepository.save(withdrawl);
        Account account = accountRepository.findById(account_id).get();
        Double newBalance = account.getBalance() - withdrawl.getAmount();
        account.setBalance(newBalance);
    }
    public void deleteWithdrawl(Long account_id, Long withdrawl_id){
        withdrawlRepository.deleteById(withdrawl_id);
        Account account = accountRepository.findById(account_id).get();
        Withdrawl withdrawl = withdrawlRepository.findById(withdrawl_id).get();
        Double newBalance = account.getBalance() + withdrawl.getAmount();
        account.setBalance(newBalance);
    }

}
