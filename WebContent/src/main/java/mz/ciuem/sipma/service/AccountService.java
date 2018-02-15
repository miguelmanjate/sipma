package mz.ciuem.sipma.service;

import mz.ciuem.sipma.entity.Account;

public interface AccountService extends GenericService<Account> {
	public Account createAccount(Account account);


}
