package com.cdac.banking.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cdac.banking.entity.Registration;

public interface RegistrationRepository extends CrudRepository<Registration, Integer> {

	@Modifying
	@Transactional
	@Query("update Registration r set r.loginId=:loginId where r.registrationId=:registrationId")
	public int updateLoginId(@Param("loginId") int loginId, @Param("registrationId") int registationId);
	
	@Query("from Registration r where r.loginId=:loginId")
	public Optional<Registration> getUser(@Param("loginId") int loginId);

}
