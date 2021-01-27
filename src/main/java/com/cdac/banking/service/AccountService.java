package com.cdac.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.banking.entity.Account;
import com.cdac.banking.entity.Registration;
import com.cdac.banking.entity.UserTransaction;
import com.cdac.banking.model.AccountResponse;
import com.cdac.banking.repo.AccountRepository;
import com.cdac.banking.repo.RegistrationRepository;

@Service
public class AccountService {

	private AccountRepository accountRepository;

	private TransactionService transactionService;

	private RegistrationRepository registrationRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository, TransactionService transactionService,
			RegistrationRepository registrationRepository) {

		this.accountRepository = accountRepository;
		this.transactionService = transactionService;
		this.registrationRepository = registrationRepository;
	}

	public AccountResponse getAccountDetails(int accountId, int limit){

		AccountResponse accountResponse = new AccountResponse("failed");
		if (accountRepository.existsById(accountId)) {
			Account userAccount = accountRepository.findById(accountId).get();
			Optional<Registration> userRegistrationDetails = registrationRepository.findById(userAccount.getLoginId());
			if (userRegistrationDetails.isPresent()) {
				Registration details = userRegistrationDetails.get();
				accountResponse.setCustomerName(details.getFirstname() + " " + details.getLastname());
			}
			accountResponse.setLoginId(userAccount.getLoginId());
			accountResponse.setAccountId(accountId);
			accountResponse.setAccountType(userAccount.getAccountType());
			accountResponse.setBalance(userAccount.getBalance());

			List<UserTransaction> recentTransactions = transactionService.fetchTransactions(accountId, limit);
			accountResponse.setTransactions(recentTransactions);
			accountResponse.setStatus("success");
		}

		return accountResponse;
	}

}
