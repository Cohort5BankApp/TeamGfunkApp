package com.william.fullbankingapplicationfinal.controller;


import com.william.fullbankingapplicationfinal.error.HttpException;
import com.william.fullbankingapplicationfinal.model.Withdrawal;
import com.william.fullbankingapplicationfinal.repository.WithdrawlRepository;
import com.william.fullbankingapplicationfinal.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class WithdrawalController {

	@Autowired
	private WithdrawalService withdrawalService;
	private WithdrawlRepository withdrawlRepository;

	@GetMapping(path = "/accounts/{accountId}/withdrawals")
	public Iterable<Withdrawal> getAllDeposit(@PathVariable Long accountId){
		Iterable<Withdrawal> AllDeposit = withdrawlRepository.findAll();

		if(AllDeposit.equals(null)){
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Account not found");
		}
		else if (AllDeposit.equals(accountId)){throw new HttpClientErrorException(HttpStatus.CREATED,null);}

		return AllDeposit;
	}

	@RequestMapping(path = "/withdrawals/{withdrawalId}", method = RequestMethod.GET)
	public Optional<Withdrawal> getWithdrawal(@PathVariable Long id) {
		Optional optional = withdrawalService.getWithdrawlById(id);

		if (!optional.isPresent()){ throw new HttpException(HttpStatus.NOT_FOUND, "error fetching withdrawal with id");}
		else if (optional.isPresent()) throw new HttpException(HttpStatus.OK, null);

		return optional;
	}

	@RequestMapping(path = "/accounts/{accountId}/withdraws", method = RequestMethod.POST)
	public Optional<?> createWithdrawal(@PathVariable Long accountId, Withdrawal withdrawal ) {
		withdrawalService.createWithdrawl(accountId,withdrawal);
		Optional<Withdrawal> optional = withdrawalService.getWithdrawlById(withdrawal.getId());

		if (!optional.isPresent()){
			throw new HttpException(HttpStatus.NOT_FOUND, "Error creating withdrawal: Account not found");}
		else if (optional.isPresent())
			throw new HttpException(HttpStatus.CREATED, "Created withdrawal and deducted it from the account");

		return optional;
	}

	@RequestMapping(path = "{accountId}/withdrawals/{withdrawId}", method = RequestMethod.PUT)
	public Optional<Withdrawal> updateWithdrawal(@PathVariable Long accountId, @PathVariable Withdrawal withdrawal) {
		withdrawalService.updateWithdrawl(accountId,withdrawal);
		Optional<Withdrawal> optional = withdrawalService.getWithdrawlById(withdrawal.getId());

		if (optional.equals(null)){
			throw new HttpException(HttpStatus.NOT_FOUND,"Withdrawal ID does not exist");}
		else if (optional.isPresent()) throw new HttpException(HttpStatus.ACCEPTED,"Accepted withdrawal modification");

		return optional;
	}


	@RequestMapping(path = "/withdrawals/{withdrawalId}", method = RequestMethod.DELETE)
	public Optional<Withdrawal> deleteWithdrawal(@PathVariable Long accountId, @PathVariable Long withdrawalId, @PathVariable Withdrawal withdrawal) {
		withdrawalService.deleteWithdrawl(accountId, withdrawalId);
		Optional optional = withdrawalService.getWithdrawlById(withdrawal.getId());

		if (optional.equals(null)){ throw new HttpException(HttpStatus.NOT_FOUND,"This id does not exist in withdrawals");}
		else if(optional.isPresent()) throw new HttpException(HttpStatus.NO_CONTENT,null);

		return optional;
	}

}