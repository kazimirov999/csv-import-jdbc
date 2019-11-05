import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    private static final String PROPERTIES_MAIL_FILE = Main.class.getClassLoader().getResource("mail.properties").getPath();
    private static final String PROPERTIES_CONFIG_FILE = Main.class.getClassLoader().getResource("config.properties").getPath();
    private static final String DATA_PATH = Main.class.getClassLoader().getResource("myDB.csv").getPath();


    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, MessagingException {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection("root","1234","jdbc:mysql://localhost:3306/new_schema");

        ImporterData importedDataToCsv = new ImportedDataToCsv(connection);
        importedDataToCsv.importData(DATA_PATH);

        MailService.of(PROPERTIES_MAIL_FILE,"1ki15b.kuchmei@gmail.com", "12345denya")
                .send(PROPERTIES_CONFIG_FILE,  "Input File", DATA_PATH);

    }
}
