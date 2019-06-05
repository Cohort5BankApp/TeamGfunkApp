package com.william.fullbankingapplicationfinal.controller;


import com.william.fullbankingapplicationfinal.model.Deposit;
import com.william.fullbankingapplicationfinal.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;
import java.util.Optional;

@RestController
public class DepositController {


	@Autowired
	private DepositRepository depositRepository;

	@RequestMapping(path = "/accounts/{accountId}/deposits",method = RequestMethod.GET)
	public ResponseEntity<Iterable<Deposit>> getAllDeposit(){
		Iterable<Deposit> AllDeposit = depositRepository.findAll();
		if(null == AllDeposit){
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Account not found");
		}

		return new ResponseEntity<>(depositRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(path = "/deposits/{depositId}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Deposit>> getDeposit(@PathVariable Long id){
		if (null== getDeposit(id)){
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"error fetching deposit with id");
		}

		return new ResponseEntity<>(depositRepository.findById(id), HttpStatus.OK);
	}

	@RequestMapping(path = "/accounts/{accountId}/deposits", method = RequestMethod.POST)
	public ResponseEntity<?> createDeposit(@RequestBody Deposit deposit){
		Deposit d = depositRepository.save(deposit);

		if (null == d)
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Error creating deposit: Account not found");

		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/deposits/{depositId}",method = RequestMethod.PUT)
	public ResponseEntity<?> updateDeposit(@RequestBody Deposit deposit, @PathVariable Long id){
		Deposit d = depositRepository.save(deposit);

		if(null == d)
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Deposit ID does not exist");

		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(path = "/deposits/{depositId}",method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteDeposit(@PathVariable Long id){
		depositRepository.deleteById(id);
		if(null == id){
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"This id does not exist in deposits");
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
