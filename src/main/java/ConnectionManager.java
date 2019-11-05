import exception.ConnectionFailedException;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    public Connection getConnection(String user, String password, String connectionUrl) {
        try {
            return DriverManager.getConnection(connectionUrl, user, password);
        }catch (Exception e){
            throw new ConnectionFailedException("Connection failed");
        }
    }
}
