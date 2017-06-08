package commands;

import database.PostgresConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateUsersTableCommand implements Command {

    private static final String CREATE_USERS_SQL = "CREATE TABLE IF NOT EXISTS users (\n" +
            "id serial PRIMARY KEY,\n" +
            "name character varying(255)\n" +
            ");";

    public CreateUsersTableCommand() {
    }

    public boolean execute() {
        try {
            PostgresConnectionManager connectionManager = PostgresConnectionManager.getInstance();
            Connection connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_USERS_SQL);
            statement.execute();

            System.out.println("CreateUsersTableCommand executed successfully");

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
