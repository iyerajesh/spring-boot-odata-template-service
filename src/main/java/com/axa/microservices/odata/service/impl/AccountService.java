package com.axa.microservices.odata.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axa.microservices.odata.persistence.dao.IAccountDao;
import com.axa.microservices.odata.persistence.model.Account;
import com.axa.microservices.odata.service.IAccountService;
import com.google.common.collect.Lists;

/*
 * @author Rajesh Iyer
 */

@Service
@Transactional
public class AccountService implements IAccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private IAccountDao dao;

	public AccountService() {
		super();
	}

	@Override
	public Account retrieveById(final int id) {

		System.out.println("In the service - calling retrieveById");
		final Account account = dao.retrieveById(id);

		logger.debug("Account retieved from the database:" + account);

		return account;
	}

	// overridden to be secured

	@Override
	@Transactional(readOnly = true)
	public List<Account> findAll() {
		return Lists.newArrayList(dao.findAll());
	}

	@Override
	public Account findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Account> findPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account create(Account entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account update(Account entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Account entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int entityId) {
		// TODO Auto-generated method stub

	}

}
