package storage;

import models.User;

import java.util.List;

public interface UserGateWay {
    List<User> getAllUsers();

    User getUserById(Integer id);

    Integer createUser(User user);

    void updateUserById(Integer id, User user);

    void removeUserById(Integer id);
}
