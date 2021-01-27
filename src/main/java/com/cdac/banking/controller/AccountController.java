package com.cdac.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.banking.model.AccountResponse;
import com.cdac.banking.service.AccountService;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*")
public class AccountController {

	private AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/{accountId}")
	public AccountResponse fetchAccountDetails(@PathVariable("accountId") int accountId,
			@RequestParam("limit") int transactionLimit){
		return accountService.getAccountDetails(accountId, transactionLimit);
	}

}
