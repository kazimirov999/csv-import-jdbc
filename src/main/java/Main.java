
import javax.mail.MessagingException;
import javax.naming.InsufficientResourcesException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {;
    private static final String MAIL_PROPERTY_PATH = Main.class.getClassLoader().getResource("mail.properties").getPath();
    private static final String DATA_PATH = Main.class.getClassLoader().getResource("categories.csv").getPath();

    public static void main(String[] args) throws IOException, MessagingException, SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory("jdbc:mysql://localhost:3306/shop_db", "root", "1234");


        MailService mailService = MailService.of(MAIL_PROPERTY_PATH, "ruslangularenko@gmail.com", "12345678");
        CsvImporter csvImporter = new CsvImporter(connectionFactory);

        mailService.send("ruslangularenko@gmail.com", "fortestaccountfff@gmail.com", "Test", MAIL_PROPERTY_PATH);
        csvImporter.importDataFromFile(DATA_PATH, "categories");

    }
}
