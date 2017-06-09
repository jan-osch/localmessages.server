package database;

import models.User;
import storage.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresUserDAO implements UserDAO {

    private final Connection connection;

    public PostgresUserDAO() {
        this.connection = PostgresConnectionManager.getInstance().getConnection();
    }

    public List<User> getAllUsers() {
        try {

            PreparedStatement statement = this.connection.prepareStatement("SELECT id, name FROM users");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<User> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(this.buildUserFromRow(resultSet));
            }

            statement.close();
            return list;

        } catch (SQLException ignored) {
            return null;
        }
    }

    public User getUserById(Integer id) {
        try {

            PreparedStatement statement = this.connection.prepareStatement("SELECT id, name FROM users WHERE id= ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            User user = this.buildUserFromRow(resultSet);
            statement.close();

            return user;

        } catch (SQLException ignored) {
            return null;
        }
    }

    public Integer createUser(User user) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("INSERT INTO users (name) VALUES (?) RETURNING ID");
            statement.setString(1, user.getName());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int id = resultSet.getInt(1);
            statement.close();

            return id;

        } catch (SQLException ignored) {
            return null;
        }
    }

    public void updateUserById(Integer id, User user) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("UPDATE users SET name = ? WHERE id = ?");

            statement.setString(1, user.getName());
            statement.setInt(2, id);
            statement.execute();

            statement.close();

        } catch (SQLException ignored) {

        }
    }

    public void removeUserById(Integer id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, id);
            statement.execute();
            statement.close();

        } catch (SQLException ignored) {

        }
    }

    private User buildUserFromRow(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt(1));
        user.setName(resultSet.getString(2));

        return user;
    }


}
