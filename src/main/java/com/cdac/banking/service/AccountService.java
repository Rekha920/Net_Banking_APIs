package com.cdac.banking.service;

import java.text.ParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.banking.entity.Account;
import com.cdac.banking.entity.Registration;
import com.cdac.banking.model.AccountResponse;
import com.cdac.banking.repo.AccountRepository;
import com.cdac.banking.repo.RegistrationRepository;

@Service
public class AccountService {

	private AccountRepository accountRepository;

	private RegistrationRepository registrationRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository, RegistrationRepository registrationRepository) {

		this.accountRepository = accountRepository;
		this.registrationRepository = registrationRepository;
	}

	public AccountResponse getAccountDetails(int accountId, int limit) throws ParseException {

		AccountResponse accountResponse = new AccountResponse("failed");
		if (accountRepository.existsById(accountId)) {
			Account userAccount = accountRepository.findById(accountId).get();
			Optional<Registration> userRegistrationDetails = registrationRepository.getUser(userAccount.getLoginId());
			if(userRegistrationDetails.isPresent()) {
				Registration details = userRegistrationDetails.get();
				accountResponse.setCustomerName(details.getFirstname() + " " + details.getLastname());
			}
			accountResponse.setLoginId(userAccount.getLoginId());
			accountResponse.setAccountId(accountId);
			accountResponse.setAccountType(userAccount.getAccountType());
			accountResponse.setBalance(userAccount.getBalance());
			accountResponse.setStatus("success");
		}

		return accountResponse;
	}

}
