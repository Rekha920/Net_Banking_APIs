package com.cdac.banking.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cdac.banking.entity.Account;
import com.cdac.banking.entity.UserTransaction;
import com.cdac.banking.model.TransactionRequest;
import com.cdac.banking.model.TransactionResponse;
import com.cdac.banking.repo.AccountRepository;
import com.cdac.banking.repo.TransactionRepository;

@Service
public class TransactionService {

	private AccountRepository accountRepository;

	private TransactionRepository transactionRepository;

	@Autowired
	public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {

		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}

	public List<UserTransaction> fetchTransactions(int accountId, int limit){
		List<UserTransaction> transactions = new ArrayList<>();

		transactions = transactionRepository.fetchTransactions(accountId,
				PageRequest.of(0, limit, Sort.by("transactionDate").descending()));

		return transactions;
	}

	public TransactionResponse transferFunds(int senderAccountId, TransactionRequest transactionRequest) {
		TransactionResponse transactionResponse = new TransactionResponse("success", "", -1);

		Optional<Account> senderAccount = accountRepository.findById(senderAccountId);
		if (senderAccount.isPresent()) {
			if (isBalanceSufficient(senderAccount.get().getBalance(), transactionRequest.getTransferAmount())) {
				Optional<Account> recieverAccount = accountRepository
						.findById(transactionRequest.getRecipientAccountId());
				if (recieverAccount.isPresent()) {
					UserTransaction senderTransaction = prepareTransaction(senderAccountId,
							transactionRequest.getRemarks(), true, senderAccount.get().getBalance(),
							transactionRequest.getTransferAmount());
					UserTransaction recieverTransaction = prepareTransaction(transactionRequest.getRecipientAccountId(),
							transactionRequest.getRemarks(), false, recieverAccount.get().getBalance(),
							transactionRequest.getTransferAmount());

					int transactionId = fireTransaction(senderTransaction, recieverTransaction, senderAccount.get(),
							recieverAccount.get());
					transactionResponse.setTransactionId(transactionId);
				}

			} else {
				transactionResponse.setFailureReason("Insufficient Funds");
				transactionResponse.setStatus("Failed");
			}
		}

		return transactionResponse;
	}

	private int fireTransaction(UserTransaction senderTransaction, UserTransaction recieverTransaction,
			Account senderAccount, Account recieverAccount) {

		UserTransaction savedTransaction = transactionRepository.save(senderTransaction);
		transactionRepository.save(recieverTransaction);

		accountRepository.updateBalance(senderAccount.getAccountId(), senderTransaction.getBalance());
		accountRepository.updateBalance(recieverAccount.getAccountId(), recieverTransaction.getBalance());

		return savedTransaction.getTransactionId();
	}

	private UserTransaction prepareTransaction(int accountId, String remarks, boolean sender, long currentBalance,
			long transactionAmount) {

		UserTransaction userTransaction = new UserTransaction();
		userTransaction.setAccountId(accountId);
		userTransaction.setRemarks(remarks);
		userTransaction.setTransactionDate(new Date());
		if (sender) {
			long balancePostDebit = currentBalance - transactionAmount;
			userTransaction.setBalance(balancePostDebit);
			userTransaction.setDebit(transactionAmount);
		} else {
			long balancePostCredit = currentBalance + transactionAmount;
			userTransaction.setBalance(balancePostCredit);
			userTransaction.setCredit(transactionAmount);
		}
		return userTransaction;
	}

	private boolean isBalanceSufficient(long balance, long transferAmount) {
		return balance >= transferAmount;
	}

}
