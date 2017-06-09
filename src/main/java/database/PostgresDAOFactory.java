package database;

import storage.DAOFactory;
import storage.MessageDAO;
import storage.UserDAO;

public class PostgresDAOFactory implements DAOFactory {

    public UserDAO createUserDAO() {
        return new PostgresUserDAO();
    }

    public MessageDAO createMessageDAO() {
        return new PostgresMessagesDAO();
    }
}
