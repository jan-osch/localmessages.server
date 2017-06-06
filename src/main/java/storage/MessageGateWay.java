package storage;

import models.Message;

import java.util.List;

public interface MessageGateWay {
    List<Message> getAllMessages();

    List<Message> getAllMessagesByCoordinates(Double latitudeFrom, Double latitudeTo, Double longitudeFrom, Double longitudeTo);

    List<Message> getAllMessagesByCoordinatesPublicOrReciever(Double latitudeFrom, Double latitudeTo, Double longitudeFrom, Double longitudeTo, Integer receiverId);

    Integer createMessage(Message message);

    void removeMessageById(Integer id);

    void updateMessagebyId(Integer id, Message message);
}
