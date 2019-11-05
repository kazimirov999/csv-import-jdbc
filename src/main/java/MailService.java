import java.io.FileNotFoundException;
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
    private final String userName;
    private final String password;

    private MailService(Session session, String userName, String password) {
        this.session = session;
        this.userName = userName;
        this.password = password;
    }



    public static MailService of(String propertiesMailFile, String userName, String password) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(propertiesMailFile));


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        return new MailService(session, userName, password);
    }

    public void send(String propertiesConfigFile, String subject, String messagePath) throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(propertiesConfigFile));

        Message message = new MimeMessage(session);


        DataSource source = new FileDataSource(messagePath);

        message.setFrom(new InternetAddress(properties.getProperty("from")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(properties.getProperty("to")));
        message.setSubject(subject);
        message.setFileName("file");
        message.setDataHandler(new DataHandler(source));

        Transport.send(message);

    }
}
