package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;encrypt=true;";
    private Connection connection;
    private static final String SQLDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    // public DatabaseConnection() throws ClassNotFoundException {
    //     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    // }

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName(SQLDriver);
            Connection connect = DriverManager.getConnection(connectionUrl);
            return connect;
        } catch (Exception e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
            return null;
        }

    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }
}