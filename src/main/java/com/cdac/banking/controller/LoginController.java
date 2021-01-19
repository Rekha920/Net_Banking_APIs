package com.cdac.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.banking.model.LoginRequest;
import com.cdac.banking.model.LoginResponse;
import com.cdac.banking.service.LoginService;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	private LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping(consumes = "application/json", path = "/login")
	public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
		return loginService.authenticateUser(loginRequest);
	}

	@PostMapping(consumes = "application/json", path = "/login/add/{id}")
	public LoginResponse addUser(@PathVariable("id") int registrationId, @RequestBody LoginRequest loginRequest) {
		return loginService.addUser(registrationId, loginRequest);
	}

}
