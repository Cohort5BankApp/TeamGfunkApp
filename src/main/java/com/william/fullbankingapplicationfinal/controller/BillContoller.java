package com.william.fullbankingapplicationfinal.controller;

import com.william.fullbankingapplicationfinal.model.Bill;
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
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }



    //Get A Bill by ID
    @RequestMapping(value = "/bills/{bill_id}", method = RequestMethod.GET)
    public Optional<Bill> getBillRecord(@PathVariable Long id)
        if (billRepository.findById(bill).isPresent()){
            throw new HttpClientErrorException(HttpStatus.CONFLICT, bill + "error fetch bill with id");
            {
                return billService.getBillById(id);
            }
        }

        //Get all Bills by specific Account
        @RequestMapping(value = "accounts/{account_id}/bills", method = RequestMethod.GET)
        public Iterable<Bill> getBillByAccountIdRecord (@PathVariable Long id)
        if(billRepository.findById(bill).isPresent()){
            throw new HttpClientErrorException(HttpStatus.CONFLICT, bill + "error fetching bills");
            { return billService.getBillByAccountId(id);
        }
    }

        //Get all Customer Bills
        @RequestMapping(value = "customers/{customers_id}/bills", method = RequestMethod.GET)
        public Iterable<Bill> getBillByAccountIdRecord (@PathVariable Long id)
        if (billRepository.findById(bill).isPresent()){
            throw new HttpClientErrorException(HttpStatus.CONFLICT, bill + "error fetching bills");
            { return billService.getBillByAccountId(id);
        }
    }

        //Delete Bill
        @RequestMapping(value = "/bills/{bill_id}", method = RequestMethod.DELETE)
        public void deleteBillRecord (@PathVariable Long id)
        if(billRepository.findById(bill).isPresent()){
        throw new HttpClientErrorException(HttpStatus.CONFLICT, bill + "This ID does not exist in bills");
            { billService.deleteBill(id);
        }
        }

        //Update Bill
        @RequestMapping(value = "/bills/{bill_id}", method = RequestMethod.PUT)
        public void updateBillRecord (@RequestBody Bill bill, @PathVariable Long id)
        if(billRepository.findById(bill).isPresent()){
            throw new HttpClientErrorException(HttpStatus.CONFLICT, bill + "Bill ID does not exist");
            { billService.updateBill(bill);
        }
    }
}

