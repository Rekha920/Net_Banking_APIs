package com.cdac.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.banking.entity.UserTransaction;
import com.cdac.banking.model.TransactionRequest;
import com.cdac.banking.model.TransactionResponse;
import com.cdac.banking.service.TransactionService;

@RestController
@CrossOrigin(origins = "*")
public class TransactionController {

	private TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping("/transactions/account/{accountId}")
	public List<UserTransaction> fetchTransactions(@PathVariable("accountId") int accountId,
	 @RequestParam("limit") int limit) {

		return transactionService.fetchTransactions(accountId, limit);
	}

	@PostMapping("/transactions/account/{accountId}")
	public TransactionResponse transferFunds(@PathVariable("accountId") int accountId,
			@RequestBody TransactionRequest transactionRequest) {

		return transactionService.transferFunds(accountId, transactionRequest);
	}

}
