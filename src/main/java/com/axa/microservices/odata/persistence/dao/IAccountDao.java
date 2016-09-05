package com.axa.microservices.odata.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.axa.microservices.odata.persistence.model.Account;

public interface IAccountDao extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {

	@Query("SELECT account FROM Account account where account.id = :id")
	Account retrieveById(int id);

}
