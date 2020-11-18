package application.framework;

import java.util.Collection;

public interface CustomerDAO {
	void saveCustomer(Customer customer);
//	void updateCustomer(Customer customer);
Customer loadCustomer(String name);
	Collection<Customer> getCustomerList();
}
