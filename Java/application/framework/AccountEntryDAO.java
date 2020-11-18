package application.framework;

import java.util.Collection;

public interface AccountEntryDAO {
	void saveAccountEntry(AccountEntry accountEntry);
//	void updateAccountEntry(AccountEntry accountEntry);
//	AccountEntry loadAccountEntry(String accountNumber);
	Collection<AccountEntry> getAccountEntries();
}
