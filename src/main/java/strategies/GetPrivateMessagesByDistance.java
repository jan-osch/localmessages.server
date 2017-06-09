package strategies;

import storage.MessageDAO;

import java.util.List;

public class GetPrivateMessagesByDistance implements GetMessagesByLocationStrategy {

    private Double distance;

    public GetPrivateMessagesByDistance(Double distance) {
        this.distance = distance;
    }

    public List<Integer> getIds(MessageDAO gateWay, Double latitude, Double longitude, Integer receiver) {
        Double latFrom = latitude - this.distance;
        Double latTo = latitude + this.distance;
        Double longFrom = longitude - this.distance;
        Double longTo = longitude + this.distance;

        return gateWay.getPrivateMessages(latFrom, latTo, longFrom, longTo, receiver);
    }
}
