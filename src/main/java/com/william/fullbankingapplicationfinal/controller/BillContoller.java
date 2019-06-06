package com.william.fullbankingapplicationfinal.controller;

import com.william.fullbankingapplicationfinal.error.HttpException;
import com.william.fullbankingapplicationfinal.model.Bill;
import com.william.fullbankingapplicationfinal.service.AccountService;
import com.william.fullbankingapplicationfinal.service.BillService;
import com.william.fullbankingapplicationfinal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@CrossOrigin (origins = "http://localhost:4200")
@RequestMapping(value = "/bills")
public class BillContoller {
    @Autowired
    public BillService billService;

    //localhost:8080/user is the REQUEST
    @RequestMapping(value = "/createBill", method = RequestMethod.POST)
    public ResponseEntity<Object> addBillRecord(Long account_id, @RequestBody Bill bill) {
        billService.createBill(account_id, bill);

        // Set the location header for the newly created resource (201)
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPersonUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bill.getBill_id())
                .toUri();
        responseHeaders.setLocation(newPersonUri);

        if (bill == null)
            throw new HttpException(HttpStatus.NOT_FOUND, "error fetching bill");
        if(bill != null)
            throw new HttpException(HttpStatus.OK, "Successful");
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

        //Get A Bill by ID
        @RequestMapping(value = "/{bill_id}", method = RequestMethod.GET)
        public Optional<Bill> getBillRecord(@PathVariable Long id){
            Optional<Bill> optionalBill = billService.getBillById(id);
            if(!optionalBill.isPresent())
                throw new HttpException(HttpStatus.NOT_FOUND, "error fetching bill");
            if(optionalBill.isPresent())
                throw new HttpException(HttpStatus.OK, "Sucsessful");

            return billService.getBillById(id);
        }


        //Delete Bill
        @RequestMapping(value = "/{bill_id}", method = RequestMethod.DELETE)
        public void deleteBillRecord (Long account_id, @PathVariable("bill_id") Long id){
            Optional<Bill> optionalBill = billService.getBillById(id);
            if(!optionalBill.isPresent())
                throw new HttpException(HttpStatus.NOT_FOUND, "error deleting bill");
            if(optionalBill.isPresent())
                throw new HttpException(HttpStatus.OK, "Successful");
            billService.deleteBill(account_id, id);
        }

        //Update Bill
        @RequestMapping(value = "/{bill_id}", method = RequestMethod.PUT)
        public void updateBillRecord (@RequestBody Bill bill, @PathVariable("bill_id") Long bill_id, Long account_id){
        Optional<Bill> optionalBill = billService.getBillById(bill_id);
            if(!optionalBill.isPresent())
                throw new HttpException(HttpStatus.NOT_FOUND, "error updating bill");
            if(!optionalBill.isPresent())
                throw new HttpException(HttpStatus.OK, "Successful");
            billService.updateBill(account_id, bill);
        }
    }

