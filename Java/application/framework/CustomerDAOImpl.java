package application.framework;

import java.util.Collection;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public void saveCustomer(Customer customer) {
		CustomerDB.customerList.add(customer);
	}

	@Override
	public Customer loadCustomer(String name) {
		for(Customer customer:CustomerDB.customerList){
			if(customer.getName().equals(name)){
				return customer;
			}
		}
		return null;
	}

	@Override
	public Collection<Customer> getCustomerList() {
		return CustomerDB.customerList;
	}
}
