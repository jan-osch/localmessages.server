package storage;

public interface GatewayFactory {
    UserGateWay createUserGateWay();

    MessageGateWay createMessageGateWay();
}
