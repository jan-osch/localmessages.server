package storage;

import models.Message;

import java.util.List;

public interface MessageDAO {
    Message getMessage(Integer id);

    List<Message> getAllMessages();

    List<Integer> getPrivateMessages(Double latitudeFrom, Double latitudeTo, Double longitudeFrom, Double longitudeTo, Integer receiverId);

    List<Integer> getPublicMessages(Double latitudeFrom, Double latitudeTo, Double longitudeFrom, Double longitudeTo);

    Integer createMessage(Message message);

    void removeMessageById(Integer id);

    void updateById(Integer id, Message message);
}
