import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CsvImporter {

    private ConnectionFactory connectionFactory;

    public CsvImporter(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void importDataFromFile(String path, String table) throws SQLException {
        String sql = "LOAD DATA  INFILE ? \n" +
                "INTO TABLE tableName \n" +
                "FIELDS TERMINATED BY ',' \n" +
                "ENCLOSED BY '\"'\n" +
                "LINES TERMINATED BY '\\n'";

        sql = sql.replace("tableName", table);
        Connection connection = connectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, path);
        preparedStatement.executeUpdate();
    }
}
