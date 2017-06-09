package storage;

public interface DAOFactory {
    UserDAO createUserDAO();

    MessageDAO createMessageDAO();
}
