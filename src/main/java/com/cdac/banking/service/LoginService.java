package com.cdac.banking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cdac.banking.entity.Account;
import com.cdac.banking.entity.LoginUser;
import com.cdac.banking.model.LoginRequest;
import com.cdac.banking.model.LoginResponse;
import com.cdac.banking.repo.AccountRepository;
import com.cdac.banking.repo.LoginRepository;
import com.cdac.banking.repo.RegistrationRepository;

@Service
public class LoginService {

	private LoginRepository loginRepository;

	private AccountRepository accountRepository;

	private RegistrationRepository registrationRepository;

	@Autowired
	public LoginService(LoginRepository loginRepository, AccountRepository accountRepository,
			RegistrationRepository registrationRepository) {

		this.loginRepository = loginRepository;
		this.accountRepository = accountRepository;
		this.registrationRepository = registrationRepository;
	}

	public LoginResponse authenticateUser(LoginRequest loginRequest) {

		LoginResponse loginResponse = new LoginResponse("failed", 0);
		Optional<LoginUser> user = loginRepository.authenticateUser(loginRequest.getUsername(),
				loginRequest.getPassword());

		if (user.isPresent()) {
			loginResponse.setStatus("success");
			Optional<Account> account = accountRepository.fetchByLoginId(user.get().getLoginId());
			if(account.isPresent()) {
				loginResponse.setAccountId(account.get().getAccountId());
			}
		}
		return loginResponse;
	}

	public LoginResponse addUser(int registrationId, LoginRequest loginRequest) {

		LoginResponse addUserResponse = new LoginResponse("failed", 0);
		LoginUser loginUser = new LoginUser();
		loginUser.setUsername(loginRequest.getUsername());
		loginUser.setPassword(loginRequest.getPassword());

		try {
			LoginUser savedUser = loginRepository.save(loginUser);
			if (savedUser.getLoginId() != 0) {
				int affectedRow = registrationRepository.updateLoginId(savedUser.getLoginId(), registrationId);
				if (affectedRow == 1) {
					Account newAccount = new Account();
					newAccount.setAccountType("Savings");
					newAccount.setLoginId(savedUser.getLoginId());
					Account savedAccount = accountRepository.save(newAccount);
					if (savedAccount.getAccountId() != 0) {
						addUserResponse.setStatus("success");
						addUserResponse.setAccountId(savedAccount.getAccountId());
					}
				}
			}
		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			addUserResponse.setStatus("duplicate");
		} catch (Exception exception) {
		}
		return addUserResponse;
	}

}
