package strategies;

import storage.MessageGateWay;

import java.util.List;

public interface GetMessagesByLocationStrategy {
    List<Integer> getMessagesIds(MessageGateWay messageGateWay, Double latitude, Double longitude, Integer receiver);
}
