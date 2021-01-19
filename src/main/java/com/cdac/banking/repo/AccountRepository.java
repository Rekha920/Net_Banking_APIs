package com.cdac.banking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.banking.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

	@Modifying
	@Transactional
	@Query("update Account a set a.balance = :balance where a.accountId =:accountId")
	int updateBalance(@Param("accountId") int accountId, @Param("balance") long balance);
	
	@Query("from Account a where a.loginId=:loginId")
	Optional<Account> fetchByLoginId(@Param("loginId") int loginId);

}
