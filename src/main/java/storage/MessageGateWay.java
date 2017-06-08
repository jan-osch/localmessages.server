package storage;

import models.Message;

import java.util.List;

public interface MessageGateWay {
    Message getMessage(Integer id);

    List<Message> getAllMessages();

    List<Integer> getMessagesByCoordinates(Double latitudeFrom, Double latitudeTo, Double longitudeFrom, Double longitudeTo, Integer receiverId);

    Integer createMessage(Message message);

    void removeMessageById(Integer id);

    void updateById(Integer id, Message message);
}
