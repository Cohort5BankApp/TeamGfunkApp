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
@RequestMapping(value = "/withdrawals")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WithdrawalController {

	@Autowired
	private WithdrawalService withdrawalService;
	@Autowired
	private WithdrawlRepository withdrawlRepository;

	@GetMapping(path = "/{accountId}/withdrawals")
	public Iterable<Withdrawal> getAllWithdrawals(@PathVariable Long accountId){
		ArrayList<Withdrawal> allDeposit = withdrawalService.getWithdrawals();

		if(allDeposit.size() < 1 ){
			throw new HttpException(HttpStatus.NOT_FOUND,"Account not found");
		}
		else if (allDeposit.size() > 0)
			throw new HttpException(HttpStatus.CREATED,null);

		return allDeposit;
	}

	@RequestMapping(path = "/{withdrawalId}", method = RequestMethod.GET)
	public Optional<Withdrawal> getWithdrawal(@PathVariable Long id) {
		Optional optional = withdrawalService.getWithdrawlById(id);

		if (!optional.isPresent())
			throw new HttpException(HttpStatus.NOT_FOUND, "error fetching withdrawal with id");
		else if (optional.isPresent())
			throw new HttpException(HttpStatus.OK, null);

		return optional;
	}

	@RequestMapping(path = "/{accountId}/withdraws", method = RequestMethod.POST)
	public Optional<?> createWithdrawal(@PathVariable Long accountId, Withdrawal withdrawal ) {
		withdrawalService.createWithdrawl(accountId,withdrawal);
		Optional<Withdrawal> optional = withdrawalService.getWithdrawlById(withdrawal.getId());

		if (!optional.isPresent())
			throw new HttpException(HttpStatus.NOT_FOUND, "Error creating withdrawal: Account not found");
		else if (optional.isPresent())
			throw new HttpException(HttpStatus.CREATED, "Created withdrawal and deducted it from the account");

		return optional;
	}

	@RequestMapping(path = "/{withdrawId}", method = RequestMethod.PUT)
	public Optional<Withdrawal> updateWithdrawal(@PathVariable Long accountId, @PathVariable Withdrawal withdrawal) {
		withdrawalService.updateWithdrawl(accountId,withdrawal);
		Optional<Withdrawal> optional = withdrawalService.getWithdrawlById(withdrawal.getId());

		if (optional.equals(null))
			throw new HttpException(HttpStatus.NOT_FOUND,"Withdrawal ID does not exist");
		if (optional.isPresent())
			throw new HttpException(HttpStatus.OK,"Accepted withdrawal modification");

		return optional;
	}


	@RequestMapping(path = "/{withdrawalId}", method = RequestMethod.DELETE)
	public Optional<Withdrawal> deleteWithdrawal(@PathVariable Long accountId, @PathVariable Long withdrawalId, @PathVariable Withdrawal withdrawal) {
		withdrawalService.deleteWithdrawl(accountId, withdrawalId);
		Optional optional = withdrawalService.getWithdrawlById(withdrawal.getId());

		if (optional.equals(null))
			throw new HttpException(HttpStatus.NOT_FOUND,"This id does not exist in withdrawals");
		if(optional.isPresent())
			throw new HttpException(HttpStatus.NO_CONTENT,null);

		return optional;
	}

}