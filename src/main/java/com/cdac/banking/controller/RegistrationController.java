package com.cdac.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.banking.entity.Registration;
import com.cdac.banking.model.RegistrationResponse;
import com.cdac.banking.service.RegistrationService;

@RestController()
@CrossOrigin(origins = "*")
public class RegistrationController {

	private RegistrationService registrationService;

	@Autowired
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@PostMapping(consumes = "application/json", value = "/registration")
	public RegistrationResponse registerUser(@RequestBody Registration registrationRequest) {
		return registrationService.registerUser(registrationRequest);
	}

	@GetMapping(value = "/profile/{loginId}")
	public Registration getUserProfile(@PathVariable(name = "loginId") int loginId) {
		return registrationService.getUser(loginId);
	}

	@PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/profile")
	public RegistrationResponse updateUserProfile(@RequestBody Registration registrationRequest) {
		return registrationService.updateUser(registrationRequest);
	}

}
