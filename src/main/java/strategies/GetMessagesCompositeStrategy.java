package strategies;

import storage.MessageDAO;

import java.util.ArrayList;
import java.util.List;

public class GetMessagesCompositeStrategy implements GetMessagesByLocationStrategy {
    private List<GetMessagesByLocationStrategy> subStrategies;


    public GetMessagesCompositeStrategy() {
        this.subStrategies = new ArrayList<>();
    }

    public void addChild(GetMessagesByLocationStrategy strategy) {
        this.subStrategies.add(strategy);
    }

    @Override
    public List<Integer> getIds(MessageDAO messageDAO, Double latitude, Double longitude, Integer receiver) {
        ArrayList<Integer> result = new ArrayList<>();

        for (GetMessagesByLocationStrategy strategy : this.subStrategies) {
            List<Integer> ids = strategy.getIds(messageDAO, latitude, longitude, receiver);
            result.removeAll(ids);
            result.addAll(ids);
        }

        return result;
    }
}
