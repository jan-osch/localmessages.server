package strategies;

import models.Message;
import storage.MessageGateWay;

import java.util.List;

public class GetMessagesBySquareSelection implements GetMessagesByLocationStrategy {

    private Double distance;

    public GetMessagesBySquareSelection(Double distance) {
        this.distance = distance;
    }

    public List<Integer> getMessagesIds(MessageGateWay gateWay, Double latitude, Double longitude, Integer receiver) {
        Double latFrom = latitude - this.distance;
        Double latTo = latitude + this.distance;
        Double longFrom = longitude - this.distance;
        Double longTo = longitude + this.distance;

        return gateWay.getMessagesByCoordinates(latFrom, latTo, longFrom, longTo, receiver);
    }
}
