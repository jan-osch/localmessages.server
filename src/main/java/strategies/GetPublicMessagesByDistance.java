package strategies;

import storage.MessageDAO;

import java.util.List;

public class GetPublicMessagesByDistance implements GetMessagesByLocationStrategy {

    private Double distance;

    public GetPublicMessagesByDistance(Double distance) {
        this.distance = distance;
    }

    public List<Integer> getIds(MessageDAO gateWay, Double latitude, Double longitude, Integer receiver) {
        Double latFrom = latitude - this.distance;
        Double latTo = latitude + this.distance;
        Double longFrom = longitude - this.distance;
        Double longTo = longitude + this.distance;

        return gateWay.getPublicMessages(latFrom, latTo, longFrom, longTo);
    }
}
