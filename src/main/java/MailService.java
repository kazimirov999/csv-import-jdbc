import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;

public class MailService {


    private final Session session;

    private MailService(Session session) {
        this.session = session;
    }



    public static MailService of(String propertiesFile, String userName, String password) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(propertiesFile));


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        return new MailService(session);
    }

    public void send(String from, String to, String subject, String messagePath) throws MessagingException {
        Message message = new MimeMessage(session);


        DataSource source = new FileDataSource(messagePath);

        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setDataHandler(new DataHandler(source));

        Transport.send(message);
    }
}
