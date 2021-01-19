package com.cdac.banking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.banking.entity.Registration;
import com.cdac.banking.model.RegistrationResponse;
import com.cdac.banking.repo.RegistrationRepository;

@Service
public class RegistrationService {

	private RegistrationRepository registrationRepository;

	@Autowired
	public RegistrationService(RegistrationRepository registrationRepository) {
		this.registrationRepository = registrationRepository;
	}

	public Registration getUser(int loginId) {

		Optional<Registration> registeredUser = registrationRepository.getUser(loginId);
		if (registeredUser.isPresent()) {
			return registeredUser.get();
		}

		Registration fallBackUser = new Registration();
		fallBackUser.setLoginId(-1);
		return fallBackUser;
	}

	public RegistrationResponse registerUser(Registration registrationRequest) {

		RegistrationResponse registrationResponse = new RegistrationResponse("failed", 0);
		try {
			Registration savedRegistration = registrationRepository.save(registrationRequest);
			if (savedRegistration.getRegistrationId() != 0) {
				registrationResponse.setStatus("success");
				registrationResponse.setRegistrationId(savedRegistration.getRegistrationId());
			}
		} catch (Exception exception) {
			// log exception here
		}

		return registrationResponse;
	}

	public RegistrationResponse updateUser(Registration registrationRequest) {

		RegistrationResponse response = new RegistrationResponse("failed", 0);

		try {
			if (registrationRepository.existsById(registrationRequest.getRegistrationId())) {
				Registration registeredUser = registrationRepository.findById(registrationRequest.getRegistrationId())
						.get();
				registeredUser.setAddress(registrationRequest.getAddress());
				registeredUser.setFirstname(registrationRequest.getFirstname());
				registeredUser.setLastname(registrationRequest.getLastname());
				registeredUser.setMobile(registrationRequest.getMobile());
				registeredUser.setPancard(registrationRequest.getPancard());
				registeredUser.setDateOfBirth(registrationRequest.getDateOfBirth());
				registrationRepository.save(registeredUser);
				response.setStatus("success");
				response.setRegistrationId(registrationRequest.getRegistrationId());
			}
		} catch (RuntimeException runtimeException) {
		}
		return response;
	}

}
