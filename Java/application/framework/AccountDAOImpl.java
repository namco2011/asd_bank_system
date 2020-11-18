package application.framework;

import java.util.ArrayList;
import java.util.Collection;

public class AccountDAOImpl implements AccountDAO {
	Collection<Account> accountlist = new ArrayList<Account>();

	public void saveAccount(Account account) {
//		accountlist.add(account); // add the new
		AccountDB.accountList.add(account);
	}

	public void updateAccount(Account account) {
		Account accountexist = loadAccount(account.getAccountNumber());
		if (accountexist != null) {
			accountlist.remove(accountexist); // remove the old
			accountlist.add(account); // add the new
//			AccountDB.accountList.remove(accountexist);
//			AccountDB.accountList.add(account);
		}
	}

	public Account loadAccount(String accountNumber) {
		for (Account account : AccountDB.accountList) {
			if (account.getAccountNumber() == accountNumber) {
				return account;
			}
		}
		return null;
	}

	public Collection<Account> getAccounts() {

//		return accountlist;
		return AccountDB.accountList;
	}

}
