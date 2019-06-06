package com.william.fullbankingapplicationfinal.service;


import com.william.fullbankingapplicationfinal.model.Account;
import com.william.fullbankingapplicationfinal.model.Deposit;
import com.william.fullbankingapplicationfinal.model.Withdrawal;
import com.william.fullbankingapplicationfinal.repository.AccountRepository;
import com.william.fullbankingapplicationfinal.repository.WithdrawlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class WithdrawalService {

    @Autowired
    private WithdrawlRepository withdrawlRepository;

    @Autowired
    private AccountRepository accountRepository;


    public Optional<Withdrawal> getWithdrawlById(Long withdrawl_id) {
        return withdrawlRepository.findById(withdrawl_id);
    }

    public void createWithdrawl(Long account_id, Withdrawal withdrawl) {
        withdrawlRepository.save(withdrawl);
        withdrawl.setAccount_id(account_id);
        Account account = accountRepository.findById(account_id).get();
        Double newBalance = account.getBalance() - withdrawl.getAmount();
        account.setBalance(newBalance);
    }

    public void updateWithdrawl(Long account_id, Withdrawal withdrawl) {
        withdrawlRepository.save(withdrawl);
        Account account = accountRepository.findById(account_id).get();
        Double newBalance = account.getBalance() - withdrawl.getAmount();
        account.setBalance(newBalance);
    }

    public void deleteWithdrawl(Long account_id, Long withdrawl_id) {
        withdrawlRepository.deleteById(withdrawl_id);
        Account account = accountRepository.findById(account_id).get();
        Withdrawal withdrawl = withdrawlRepository.findById(withdrawl_id).get();
        Double newBalance = account.getBalance() + withdrawl.getAmount();
        account.setBalance(newBalance);
    }

    public ArrayList<Withdrawal> getWithdrawals() {
        Iterable<Withdrawal> withdrawals = withdrawlRepository.findAll();
        ArrayList<Withdrawal> these_withdrawals = new ArrayList<>();
        for (Withdrawal withdrawal : withdrawals) {
            these_withdrawals.add(withdrawal);
        }

        return these_withdrawals;
    }
}