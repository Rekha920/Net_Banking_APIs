package com.cdac.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.banking.entity.Payee;
import com.cdac.banking.model.GenericResponse;
import com.cdac.banking.model.PayeeRequest;
import com.cdac.banking.service.PayeeService;

@RestController
@RequestMapping("/payee")
@CrossOrigin(origins = "*")
public class PayeeController {

	private PayeeService payeeService;

	@Autowired
	public PayeeController(PayeeService payeeService) {
		this.payeeService = payeeService;
	}

	@PostMapping()
	public GenericResponse addPayee(@RequestParam("accountId") int accountId, @RequestBody PayeeRequest payeeRequest) {
		return payeeService.addPayee(accountId, payeeRequest);
	}

	@GetMapping()
	public List<Payee> getPayees(@RequestParam("accountId") int accountId) {
		return payeeService.fetchPayees(accountId);
	}
}
