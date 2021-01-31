package com.cdac.banking.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdac.banking.entity.UserTransaction;

@Repository
public interface TransactionRepository extends CrudRepository<UserTransaction, Integer> {

	@Query("from UserTransaction t where t.accountId =:accountId")
	List<UserTransaction> fetchTransactions(@Param("accountId") int accountId, Pageable pageable);
}
