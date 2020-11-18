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

        AccountEntry accountentry1 = new AccountEntry(100, "Deposit", "11111111","CU001");
        accountentry1.setDate(LocalDate.now().minusDays(30));
        AccountEntry accountentry2 = new AccountEntry(-100, "Withdwaw", "11111111","CU001");
        accountentry2.setDate(LocalDate.now().minusDays(45));
        AccountEntry accountentry3 = new AccountEntry(10000, "Deposit", "11111111","CU001");
        accountentry3.setDate(LocalDate.now().minusDays(60));
        AccountEntry accountentry4 = new AccountEntry(50000, "Deposit", "22222222","CU002");
        accountentry4.setDate(LocalDate.now().minusDays(90));
        accountEntries.add(accountentry1);
        accountEntries.add(accountentry2);
        accountEntries.add(accountentry3);
        accountEntries.add(accountentry4);

    }

}
