package com.cdac.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.banking.entity.Payee;
import com.cdac.banking.model.GenericResponse;
import com.cdac.banking.model.PayeeRequest;
import com.cdac.banking.repo.PayeeRepository;
import com.mysql.cj.util.StringUtils;

@Service
public class PayeeService {

	private PayeeRepository payeeRepository;

	@Autowired
	public PayeeService(PayeeRepository payeeRepository) {
		this.payeeRepository = payeeRepository;
	}

	public GenericResponse addPayee(int accountId, PayeeRequest payeeRequest) {

		GenericResponse payeeResponse = new GenericResponse("failed");

		Payee payee = new Payee();
		payee.setPayeeName(payeeRequest.getPayeeName());
		payee.setPayeeAccountNumber(payeeRequest.getPayeeAccountNo());
		payee.setLinkedAccountId(accountId);
		Payee savedPayee = payeeRepository.save(payee);
		if (savedPayee.getPayeeId() != 0) {
			payeeResponse.setStatus("success");
		}
		return payeeResponse;
	}

	public List<Payee> fetchPayees(int accountId) {
		return payeeRepository.getAllPayeesByAccountId(accountId);
	}

	public GenericResponse updatePayee(int payeeId, PayeeRequest payeeRequest) {

		GenericResponse payeeResponse = new GenericResponse("failed");

		if (payeeRepository.existsById(payeeId)) {
			Payee savedPayee = payeeRepository.findById(payeeId).get();
			if (!StringUtils.isNullOrEmpty(payeeRequest.getPayeeName())) {
				savedPayee.setPayeeName(payeeRequest.getPayeeName());
			}
			if (payeeRequest.getPayeeAccountNo() != 0) {
				savedPayee.setPayeeAccountNumber(payeeRequest.getPayeeAccountNo());
			}
			payeeRepository.save(savedPayee);
			payeeResponse.setStatus("success");
		}
		return payeeResponse;
	}
}
