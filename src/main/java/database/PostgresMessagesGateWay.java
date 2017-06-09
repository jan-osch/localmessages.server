package database;

import models.Message;
import storage.MessageGateWay;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostgresMessagesGateWay implements MessageGateWay {

    private final Connection connection;

    public PostgresMessagesGateWay() {
        this.connection = PostgresConnectionManager.getInstance().getConnection();
    }

    public List<Message> getAllMessages() {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT id, created_at, lat, long, receivers, sender, is_public, payload FROM messages"
            );

            ResultSet resultSet = statement.executeQuery();
            ArrayList<Message> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(this.buildMessage(resultSet));
            }

            statement.close();
            return list;
        } catch (SQLException err) {
            err.printStackTrace();
            return null;
        }
    }

    public Message getMessage(Integer id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT id, created_at, lat, long, receivers, sender, is_public, payload FROM messages WHERE id = ?"
            );
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            Message message = this.buildMessage(resultSet);
            statement.close();
            return message;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Integer> getMessagesByCoordinates(Double latitudeFrom, Double latitudeTo, Double longitudeFrom, Double longitudeTo, Integer receiverId) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT id FROM messages " +
                            "WHERE long >= ? AND long <= ? AND lat >= ? AND lat <= ? AND " +
                            "(is_public = TRUE OR ? = ANY(receivers))"
            );
            statement.setDouble(1, longitudeFrom);
            statement.setDouble(2, longitudeTo);
            statement.setDouble(3, latitudeFrom);
            statement.setDouble(4, longitudeTo);
            statement.setInt(5, receiverId);

            ResultSet resultSet = statement.executeQuery();
            ArrayList<Integer> integers = new ArrayList<>();
            while (resultSet.next()) {
                integers.add(resultSet.getInt(1));
            }
            statement.close();
            return integers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer createMessage(Message message) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "INSERT INTO messages (created_at, lat, long, receivers, sender, is_public, payload) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id"
            );
            Integer[] receivers = message.getReceiversIds().toArray(new Integer[message.getReceiversIds().size()]);
            Array receiversAsArray = this.connection.createArrayOf("int", receivers);

            statement.setObject(1, message.getCreatedAt());
            statement.setDouble(2, message.getLatitude());
            statement.setDouble(3, message.getLongitude());
            statement.setArray(4, receiversAsArray);
            statement.setInt(5, message.getSenderId());
            statement.setBoolean(6, message.getPublic());
            statement.setString(7, message.getPayload());

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int createdMessageId = resultSet.getInt(1);
            statement.close();
            return createdMessageId;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeMessageById(Integer id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("DELETE from messages WHERE id = ?");
            statement.setInt(1, id);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateById(Integer id, Message message) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE messages SET created_at = ?, lat = ?, long =?, receivers =?, sender = ?, is_public = ?, payload =? " +
                            "WHERE id = ?"
            );
            Integer[] receivers = message.getReceiversIds().toArray(new Integer[message.getReceiversIds().size()]);
            Array receiversAsArray = this.connection.createArrayOf("int", receivers);

            statement.setObject(1, message.getCreatedAt());
            statement.setDouble(2, message.getLatitude());
            statement.setDouble(3, message.getLongitude());
            statement.setArray(4, receiversAsArray);
            statement.setInt(5, message.getSenderId());
            statement.setBoolean(6, message.getPublic());
            statement.setString(7, message.getPayload());
            statement.setInt(8, id);

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Message buildMessage(ResultSet resultSet) throws SQLException {
        Message message = new Message();

        message.setId(resultSet.getInt("id"));
        message.setCreatedAt(resultSet.getObject("created_at", LocalDateTime.class));
        message.setLatitude(resultSet.getDouble("lat"));
        message.setLongitude(resultSet.getDouble("long"));
        message.setReceiversIds(parseReceivers(resultSet));
        message.setPayload(resultSet.getString("payload"));
        message.setPublic(resultSet.getBoolean("is_public"));
        message.setSenderId(resultSet.getInt("sender"));

        return message;
    }

    private List<Integer> parseReceivers(ResultSet resultSet) throws SQLException {
        Array receiversAsArray = resultSet.getArray("receivers");

        Integer[] rec = (Integer[]) receiversAsArray.getArray();

        return Arrays.asList(rec);
    }
}
