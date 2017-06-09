package strategies;

import storage.MessageDAO;

import java.util.List;

public interface GetMessagesByLocationStrategy {
    List<Integer> getIds(MessageDAO messageDAO, Double latitude, Double longitude, Integer receiver);
}
