package com.cdac.banking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdac.banking.entity.LoginUser;

@Repository
public interface LoginRepository extends CrudRepository<LoginUser, Integer> {

	@Query("from LoginUser u where u.username=:name and u.password=:pass")
	public Optional<LoginUser> authenticateUser(@Param("name") String username, @Param("pass") String password);

}
