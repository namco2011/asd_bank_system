package application.utilities;

import java.time.LocalDate;

public class Utilities {
    public static String generateCardNumber () {

            long max = 1999999998;
            long min = 1000000000;
            long range = max - min + 1;
            long rand = (long)(Math.random() * range) + min;
            return String.valueOf("622126"+rand);
        }
        public static String generate_ExpireDate() {
            LocalDate date = LocalDate.now().plusYears(3);
            return date.toString();
        }

    public static String generateAccount () {

        long max = 1999999998;
        long min = 1000000000;
        long range = max - min + 1;
        long rand = (long)(Math.random() * range) + min;
        return String.valueOf("2000"+rand);
    }
    public static String generateComAccount () {

        long max = 1999999998;
        long min = 1000000000;
        long range = max - min + 1;
        long rand = (long)(Math.random() * range) + min;
        return String.valueOf("1000"+rand);
    }


}

