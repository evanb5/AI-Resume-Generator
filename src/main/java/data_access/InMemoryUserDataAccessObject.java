// data_access/InMemoryUserDataAccessObject.java
package data_access;

import entity.User;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements UserDataAccessInterface {
    private Map<String, User> users;

    public InMemoryUserDataAccessObject() {
        this.users = new HashMap<>();
    }

    @Override
    public void saveUser(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }

    @Override
    public void updateUser(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public void deleteUser(String username) {
        users.remove(username);
    }
}
