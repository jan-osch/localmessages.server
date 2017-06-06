package database;


import java.sql.*;
import java.util.HashMap;

public class PostgreSQLJDBC {
    static PostgreSQLJDBC instance = null;
    static Connection connection = null;
    static HashMap<ResultSet, Statement> statementsToClose;

    private PostgreSQLJDBC() {
        connection = createConnection();
        statementsToClose = new HashMap<>();
    }

    public static PostgreSQLJDBC getInstance() {
        if (instance == null) {
            instance = new PostgreSQLJDBC();
        }
        return instance;
    }

    private Connection createConnection() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/localmessages", "localmessages", "localmessages");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

    public static void executeSql(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }

    public static Statement createStatement() throws SQLException {
        return connection.createStatement();
    }
}