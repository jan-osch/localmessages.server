package commands;

import database.PostgresConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateMessagesTableCommand implements Command {
    private static final String CREATE_MESSAGES_SQL = "CREATE TABLE IF NOT EXISTS messages (\n" +
            "    id serial PRIMARY KEY,\n" +
            "    created_at timestamp,\n" +
            "    lat double precision,\n" +
            "    long double precision,\n" +
            "    receivers integer[],\n" +
            "    sender integer,\n" +
            "    is_public  bool,\n" +
            "    payload character varying(255)\n" +
            ");";

    public boolean execute() {
        try {
            PostgresConnectionManager connectionManager = PostgresConnectionManager.getInstance();
            Connection connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_MESSAGES_SQL);
            statement.execute();

            System.out.println("CreateMessagesTableCommand executed successfully");

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
