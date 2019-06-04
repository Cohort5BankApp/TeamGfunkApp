package com.william.fullbankingapplicationfinal.service;

import com.william.fullbankingapplicationfinal.model.Bill;
import com.william.fullbankingapplicationfinal.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService {

    @Autowired
    BillRepository billRepository;

    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);
    }

    public Iterable<Bill> getAllBills() {
        return customerRepository.findAll();
    }

    public void createBill(Long id, Bill bill) {
        billRepository.save(id, bill);
    }

    public void updateBill(Bill bill) {
        billRepository.save(bill);
    }

    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        if(billRepository.existsById(id)){
            return true;
        } else {
            return false;
        }
    }

}
