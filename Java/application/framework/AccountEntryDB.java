package application.framework;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AccountEntryDB {
    public static List<AccountEntry> accountEntries = new ArrayList<>();
    static {
//        LocalDateTime localDateTime = LocalDateTime.now().minusDays(30);
//        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.systemDefault());
//        Instant instant = zonedDateTime.toInstant();
//        Date date = Date.from(instant);

//        AccountEntry accountentry1 = new AccountEntry(100, "Deposit", "1111","Jhon");
//        accountentry1.setDate(LocalDate.now().minusDays(30));
//        AccountEntry accountentry2 = new AccountEntry(2000, "Deposit", "2222","Jhon");
//        accountentry2.setDate(LocalDate.now().minusDays(45));
//        AccountEntry accountentry3 = new AccountEntry(10000, "Deposit", "3333","Frank");
//        accountentry3.setDate(LocalDate.now().minusDays(60));
//        AccountEntry accountentry4 = new AccountEntry(50000, "Deposit", "4444","Frank");
//        accountentry4.setDate(LocalDate.now().minusDays(90));
//        accountEntries.add(accountentry1);
//        accountEntries.add(accountentry2);
//        accountEntries.add(accountentry3);
//        accountEntries.add(accountentry4);


        AccountEntry accountentry5 = new AccountEntry(-10000, "charge", "6666","Jhon");
        accountentry5.setDate(LocalDate.now().minusDays(1));
        AccountEntry accountentry6 = new AccountEntry(-2000, "charge", "6666","Jhon");
        accountentry6.setDate(LocalDate.now().minusDays(2));

        AccountEntry accountentry7 = new AccountEntry(-8000, "charge", "5555","Frank");
        accountentry7.setDate(LocalDate.now().minusDays(1));
        AccountEntry accountentry8 = new AccountEntry(-5000, "charge", "5555","Frank");
        accountentry8.setDate(LocalDate.now().minusDays(2));
        accountEntries.add(accountentry5);
        accountEntries.add(accountentry6);
        accountEntries.add(accountentry7);
        accountEntries.add(accountentry8);

    }

}
