package com.william.fullbankingapplicationfinal.controller;


import com.william.fullbankingapplicationfinal.error.HttpException;
import com.william.fullbankingapplicationfinal.model.Deposit;
import com.william.fullbankingapplicationfinal.model.Withdrawal;
import com.william.fullbankingapplicationfinal.repository.DepositRepository;
import com.william.fullbankingapplicationfinal.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(value ="/deposits")
public class DepositController {


	@Autowired
	private DepositService depositService;
	@Autowired
	private DepositRepository depositRepository;

	@RequestMapping(path = "/accounts/{accountId}/deposits",method = RequestMethod.GET)
	public Iterable<Deposit> getAllDeposit(@PathVariable Long account_id){
		ArrayList<Deposit> allDeposits = depositService.getDeposits();


		if(allDeposits.size() < 1)
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Account not found");

		if (allDeposits.size() > 0)
			throw new HttpClientErrorException(HttpStatus.OK, "success");

		return allDeposits;
	}

	@RequestMapping(path = "/{depositId}", method = RequestMethod.GET)
	public Optional<Deposit> getDeposit(@PathVariable Long id) {
		Optional optional = depositService.getDepositById(id);

		if (!optional.isPresent())
			throw new HttpException(HttpStatus.NOT_FOUND, "error fetching withdrawal with id");
		if (optional.isPresent())
			throw new HttpException(HttpStatus.OK, "success");

		return optional;
	}

	@RequestMapping(path = "/{accountId}/deposits", method = RequestMethod.POST)
	public Optional<Deposit> createDeposit (@PathVariable Long accountId, @PathVariable Deposit deposit) {
		depositService.createDeposit(accountId,deposit);
		Optional <Deposit>optional = depositService.getDepositById(deposit.getId());

		if (!optional.isPresent())
			throw new HttpException(HttpStatus.NOT_FOUND, "Error creating withdrawal: Account not found");
		if (optional.isPresent())
			throw new HttpException(HttpStatus.CREATED, "Created withdrawal and deducted it from the account");

		return optional;
	}

	@RequestMapping(path = "/{depositId}",method = RequestMethod.PUT)
	public Optional<Deposit> updateDeposit(@RequestBody Deposit deposit, @PathVariable Long accountId){
		depositService.updateDeposit(accountId,deposit);

		Optional<Deposit> entity = depositService.getDepositById(deposit.getId());

		if(!entity.isPresent())
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Deposit ID does not exist");

		if (entity.isPresent())
			throw new HttpClientErrorException(HttpStatus.OK, "success");

		return entity;
	}

	@RequestMapping(path = "/{depositId}",method = RequestMethod.DELETE)
		public Optional<Deposit> deleteDeposit(@PathVariable Long id, @PathVariable Long accountid){
		depositService.deleteDeposit(id,accountid);

		Optional<Deposit> optional = depositService.getDepositById(id);

		if(!optional.isPresent())
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"This id does not exist in deposits");
		if (optional.isPresent())
			throw new HttpClientErrorException(HttpStatus.OK, "success");

		return optional;
	}

}
