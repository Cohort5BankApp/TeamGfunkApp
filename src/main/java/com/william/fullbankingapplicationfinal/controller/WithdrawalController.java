package com.william.fullbankingapplicationfinal.controller;


import com.william.fullbankingapplicationfinal.model.Withdrawal;
import com.william.fullbankingapplicationfinal.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class WithdrawalController {

	@Autowired
	private WithdrawRepository withdrawRepository;

	@GetMapping(path = "/accounts/{accountId}/withdrawals")
	public Optional<Withdrawal> getAllWithdrawals(@PathVariable Long id){

		ArrayList<Optional> withdrawal = withdrawRepository.findAll(id);



	}

	@RequestMapping(path = "/withdrawals/{withdrawalId}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Withdrawal>> getWithdrawal(@PathVariable Long id){
		return new ResponseEntity<>(withdrawRepository.findById(id), HttpStatus.OK);
	}

	@RequestMapping(path = "/accounts/{accountId}/withdraws", method = RequestMethod.POST)
	public ResponseEntity<?> createWithdrawal(@RequestBody Withdrawal withdrawal){
		withdrawRepository.save(withdrawal);
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/withdrawals/{withdrawId}",method = RequestMethod.PUT)
	public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable Long id){
		withdrawRepository.save(withdrawal);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(path = "/withdrawals/{withdrawalId}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteWithdrawal(@PathVariable Long id){
		withdrawRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
