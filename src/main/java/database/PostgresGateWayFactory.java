package database;

import storage.GatewayFactory;
import storage.MessageGateWay;
import storage.UserGateWay;

public class PostgresGateWayFactory implements GatewayFactory {

    public UserGateWay createUserGateWay() {
        return new PostgresUserGateWay();
    }

    public MessageGateWay createMessageGateWay() {
        return new PostgresMessagesGateWay();
    }
}
