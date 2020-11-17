package banking.utilities;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class SendEmail {
    public static void main(String[] args) throws IOException {

            SendEmail email = new SendEmail();
            email.SendEMail("hanhduy.nguyen@gmail.com","Testing");

    }

    public void SendEMail(String receipient , String content) throws IOException {

        final String username = "asdproject287@gmail.com";
        final String password = "ASDProject123";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("asdproject287@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("hanhduy.nguyen@gmail.com, to_username_b@yahoo.com")
            );
            message.setSubject("Email from ASD Banking");
            message.setText(content);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
