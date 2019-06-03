package com.william.fullbankingapplicationfinal.controller;


import com.william.fullbankingapplicationfinal.model.Deposit;
import com.william.fullbankingapplicationfinal.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DepositController {

	@Autowired
	private DepositRepository depositRepository;

	@RequestMapping(path = "/accounts/{accountId}/deposits",method = RequestMethod.GET)
	public ResponseEntity<Iterable<Deposit>> getAllDeposit(){
		Iterable<Deposit> AllDeposit = depositRepository.findAll();
		return new ResponseEntity<>(depositRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(path = "/deposits/{depositId}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Deposit>> getDeposit(@PathVariable Long id){
		return new ResponseEntity<>(depositRepository.findById(id), HttpStatus.OK);
	}

	@RequestMapping(path = "/accounts/{accountId}/deposits", method = RequestMethod.POST)
	public ResponseEntity<?> createDeposit(@RequestBody Deposit deposit){
		depositRepository.save(deposit);
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/deposits/{depositId}",method = RequestMethod.PUT)
	public ResponseEntity<?> updateDeposit(@RequestBody Deposit deposit, @PathVariable Long id){
		depositRepository.save(deposit);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(path = "/deposits/{depositId}",method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteDeposit(@PathVariable Long id){
		depositRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
