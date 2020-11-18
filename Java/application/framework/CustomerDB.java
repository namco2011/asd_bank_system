package application.framework;

import java.util.ArrayList;
import java.util.List;

public class CustomerDB {


    public static List<Customer> customerList = new ArrayList<>();

    //String AccountNumber, String name, String emailAddress, String street, String city, String state, String zip
    static{
        Customer customerAccount1 = new Customer( "Jhon", "jhon@gmail.com", "101 street", "Fairfield", "Iowa", "52556");
        Customer customerAccount2 = new Customer("Frank", "frank@gmail.com", "102 street", "Fairfield", "Iowa", "52556");

        customerList.add(customerAccount1);
        customerList.add(customerAccount2);
    }

}
