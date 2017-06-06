package database;

import models.Message;
import storage.MessageGateWay;

import java.util.List;

public class PostgresMessagesGateWay implements MessageGateWay {


    private static final String tableName = "messages";

    @Override
    public List<Message> getAllMessages() {
//        try {
//            String sql = "SELECT id, created_at, lat, long, receivers, sender, is_public, payload FROM :tableName";
//            sql = SqlUtils.addParam(sql, "tableName", tableName);
//
//            Statement statement = PostgreSQLJDBC.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//            resultSet.next();
//
//            ArrayList<User> list = new ArrayList<>();
//
//            while (resultSet.next()) {
////                list.add(this.buildUserFromRow(resultSet));
//            }
//
//            statement.close();
//            return list;
//
//        } catch (SQLException ignored) {
//            return null;
//        }
        //INSERT INTO messages (created_at, lat, long, recievers, sender, is_public, payload) VALUES (
//        (TIMESTAMP '2017-06-06 21:22:01'), 123.231, 53.22, '{1, 2}', 3, FALSE, 'hello world' ) RETURNING id;
        return null;
    }

    @Override
    public List<Message> getAllMessagesByCoordinates(Double latitudeFrom, Double latitudeTo, Double longitudeFrom, Double longitudeTo) {
        return null;
    }

    @Override
    public List<Message> getAllMessagesByCoordinatesPublicOrReciever(Double latitudeFrom, Double latitudeTo, Double longitudeFrom, Double longitudeTo, Integer receiverId) {
        return null;
    }

    @Override
    public Integer createMessage(Message message) {
        return null;
    }

    @Override
    public void removeMessageById(Integer id) {

    }

    @Override
    public void updateMessagebyId(Integer id, Message message) {

    }
}
