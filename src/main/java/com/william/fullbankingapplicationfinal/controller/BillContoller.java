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
public class BillContoller {
    @Autowired
    public BillService billService;

    //localhost:8080/user is the REQUEST
    @RequestMapping(value = "/bill", method = RequestMethod.POST)
    public ResponseEntity<Object> addBillRecord(@RequestBody Bill bill) {
        billService.createBill(bill);

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
            throw new HttpException(HttpStatus.OK, "Succsessful");
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

        //Get A Bill by ID
        @RequestMapping(value = "/bills/{bill_id}", method = RequestMethod.GET)
        public Optional<Bill> getBillRecord(@PathVariable Long id){
            Optional<Bill> optionalBill = billService.getBillById(id);
            if(!optionalBill.isPresent())
                throw new HttpException(HttpStatus.NOT_FOUND, "error fetching bill");
            if(optionalBill.isPresent())
                throw new HttpException(HttpStatus.OK, "Sucsessful");

            return billService.getBillById(id);
        }


        //Delete Bill
        @RequestMapping(value = "/bills/{bill_id}", method = RequestMethod.DELETE)
        public void deleteBillRecord (@PathVariable Long id){
            Optional<Bill> optionalBill = billService.getBillById(id);
            if(!optionalBill.isPresent())
                throw new HttpException(HttpStatus.NOT_FOUND, "error deleting bill");
            if(optionalBill.isPresent())
                throw new HttpException(HttpStatus.OK, "Succsessful");
            billService.deleteBill(id);
        }

        //Update Bill
        @RequestMapping(value = "/bills/{bill_id}", method = RequestMethod.PUT)
        public void updateBillRecord (@RequestBody Bill bill, @PathVariable Long id){
        Optional<Bill> optionalBill = billService.getBillById(id);
            if(!optionalBill.isPresent())
                throw new HttpException(HttpStatus.NOT_FOUND, "error updating bill");
            if(!optionalBill.isPresent())
                throw new HttpException(HttpStatus.OK, "Succsessful");
            billService.updateBill(bill);
        }
    }


