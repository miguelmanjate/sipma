package mz.ciuem.sipma.service.impl;

import mz.ciuem.sipma.entity.Account;
import mz.ciuem.sipma.entity.SubScriber;
import mz.ciuem.sipma.service.AccountService;
import mz.ciuem.sipma.service.SubScriberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl extends GenericServiceImpl<Account>implements AccountService {

	@Autowired
	private SubScriberService subScriberService;


	public Account createAccount(Account account) {

		for (SubScriber s : account.getSubScribers()) {
			s.setAccount(account);
			subScriberService.create(s);

		}
		specificDao.create(account);
		return account;
	}


}
