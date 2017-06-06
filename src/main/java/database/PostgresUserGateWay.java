package database;

import models.User;
import storage.UserGateWay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgresUserGateWay implements UserGateWay {

    private final static String tableName = "users";

    @Override
    public List<User> getAllUsers() {
        try {
            String sql = "SELECT id, name FROM :tableName";
            sql = SqlUtils.addParam(sql, "tableName", tableName);

            Statement statement = PostgreSQLJDBC.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();

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

    @Override
    public User getUserById(Integer id) {
        try {
            String sql = "SELECT id, name FROM :tableName WHERE id=:id";
            sql = SqlUtils.addParam(sql, "tableName", tableName);
            sql = SqlUtils.addParam(sql, "id", id.toString());

            Statement statement = PostgreSQLJDBC.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();

            User user = this.buildUserFromRow(resultSet);
            statement.close();

            return user;

        } catch (SQLException ignored) {
            return null;
        }
    }

    @Override
    public Integer createUser(User user) {
        try {
            String sql = "INSERT INTO :tableName (name) VALUES (':name') RETURNING ID";
            sql = SqlUtils.addParam(sql, "tableName", tableName);
            sql = SqlUtils.addParam(sql, "name", user.getName());

            Statement statement = PostgreSQLJDBC.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();

            int id = resultSet.getInt(1);
            statement.close();

            return id;

        } catch (SQLException ignored) {
            return null;
        }
    }

    @Override
    public void updateUserById(Integer id, User user) {
        try {
            String sql = "UPDATE :tableName SET name = ':name' WHERE id = :id";
            sql = SqlUtils.addParam(sql, "tableName", tableName);
            sql = SqlUtils.addParam(sql, "id", id.toString());
            sql = SqlUtils.addParam(sql, "name", user.getName());

            Statement statement = PostgreSQLJDBC.createStatement();
            statement.execute(sql);
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

    @Override
    public void removeUserById(Integer id) {
        try {
            String sql = "DELETE FROM :tableName WHERE id = :id";
            sql = SqlUtils.addParam(sql, "tableName", tableName);
            sql = SqlUtils.addParam(sql, "id", id.toString());

            Statement statement = PostgreSQLJDBC.createStatement();
            statement.execute(sql);
            statement.close();

        } catch (SQLException ignored) {

        }
    }


}
