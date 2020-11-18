package application.framework;

import java.util.Collection;

public class AccountEntryDAOImpl implements AccountEntryDAO {

	@Override
	public void saveAccountEntry(AccountEntry accountEntry) {
		AccountEntryDB.accountEntries.add(accountEntry);
	}

	@Override
	public Collection<AccountEntry> getAccountEntries() {
		return AccountEntryDB.accountEntries;
	}
}
