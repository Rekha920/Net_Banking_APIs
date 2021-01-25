package com.cdac.banking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdac.banking.entity.Payee;

@Repository
public interface PayeeRepository extends CrudRepository<Payee, Integer> {

	@Query("from Payee p where p.linkedAccountId =:accountId")
	List<Payee> getAllPayeesByAccountId(@Param("accountId") int accountId);

}
