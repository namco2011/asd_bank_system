package application.framework;

import java.util.ArrayList;
import java.util.List;

public class CustomerDB {


    public static List<Customer> customerList = new ArrayList<>();

    //String AccountNumber, String name, String emailAddress, String street, String city, String state, String zip
    static{
        Customer customer1= new Customer( "Jhon", "hanhduy.nguyen@gmail.com", "101 street", "Fairfield", "Iowa", "52556");
        Customer customer2 = new Customer("Frank", "frank@gmail.com", "102 street", "Fairfield", "Iowa", "52556");

        customerList.add(customer1);
        customerList.add(customer2);
    }

}
