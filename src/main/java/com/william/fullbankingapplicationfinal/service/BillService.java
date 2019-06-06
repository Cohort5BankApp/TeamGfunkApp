package com.william.fullbankingapplicationfinal.service;

import com.william.fullbankingapplicationfinal.model.Account;
import com.william.fullbankingapplicationfinal.model.Bill;
import com.william.fullbankingapplicationfinal.repository.AccountRepository;
import com.william.fullbankingapplicationfinal.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);
    }

    public Iterable<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public void createBill(Long account_id, Bill bill) {
        billRepository.save(bill);
        Account account = accountRepository.findById(account_id).get();
        Double newBalance = account.getBalance() - bill.getPayment_amount();
        account.setBalance(newBalance);
    }

    public void updateBill(Long account_id, Bill bill) {
        billRepository.save(bill);
        Account account = accountRepository.findById(account_id).get();
        Double newBalance = account.getBalance() - bill.getPayment_amount();
        account.setBalance(newBalance);
    }

    public void deleteBill(Long account_id, Long bill_id) {
        billRepository.deleteById(bill_id);
        Bill bill = billRepository.findById(bill_id).get();
        Account account = accountRepository.findById(account_id).get();
        Double newBalance = account.getBalance() + bill.getPayment_amount();
        account.setBalance(newBalance);
    }

    public boolean existsById(Long id) {
        if(billRepository.existsById(id)){
            return true;
        } else {
            return false;
        }
    }

}
