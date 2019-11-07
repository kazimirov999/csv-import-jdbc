import exception.ExecuteQueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ImportedDataToCsv implements ImporterData {
    public Connection connection;

    public ImportedDataToCsv(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void importData(String path) {
        PreparedStatement preparedStatement;
        String query =  "LOAD DATA  INFILE  ?  \n" +
                "INTO TABLE product \n" +
                "FIELDS TERMINATED BY ',' \n" +
                "ENCLOSED BY '\"'\n" +
                "LINES TERMINATED BY '\\n'" ;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, path);
            preparedStatement.executeQuery();

        } catch(Exception e) {

            throw new ExecuteQueryException("Query not execute "+ e.getMessage());
        }
    }
}

