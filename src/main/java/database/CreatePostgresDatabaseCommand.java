package database;

import java.sql.SQLException;

public class CreatePostgresDatabaseCommand {

    private static final String CREATE_MESSAGES_SQL = "CREATE TABLE IF NOT EXISTS messages (\n" +
            "    id serial PRIMARY KEY,\n" +
            "    created_at timestamp,\n" +
            "    lat double precision,\n" +
            "    long double precision,\n" +
            "    recievers integer[],\n" +
            "    sender integer,\n" +
            "    is_public  bool,\n" +
            "    payload character varying(255)\n" +
            ");";

    private static final String CREAT_USERS_SQL = "CREATE TABLE IF NOT EXISTS users (\n" +
            "id serial PRIMARY KEY,\n" +
            "name character varying(255)\n" +
            ");";

    public CreatePostgresDatabaseCommand() {
    }

    public boolean execute() {
        try {
            PostgreSQLJDBC.executeSql(CREAT_USERS_SQL);
            PostgreSQLJDBC.executeSql(CREATE_MESSAGES_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
