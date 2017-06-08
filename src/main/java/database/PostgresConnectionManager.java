package database;


import java.sql.*;

public class PostgresConnectionManager {
    private Connection connection = null;
    private static PostgresConnectionManager instance = null;

    private PostgresConnectionManager() {
        connection = createConnection();
    }

    public static PostgresConnectionManager getInstance() {
        if (instance == null) {
            instance = new PostgresConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private Connection createConnection() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/localmessages",
                            "localmessages",
                            "localmessages"
                    );
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

}