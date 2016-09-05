package com.axa.microservices.odata.service;

import com.axa.microservices.odata.persistence.model.Account;

/*
 * @author Rajesh Iyer
 */

public interface IAccountService extends com.axa.microservices.odata.persistence.IOperations<Account> {

	Account retrieveById(int id);

}
