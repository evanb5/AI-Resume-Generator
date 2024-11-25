// data_access/UserDataAccessInterface.java
package data_access;

import entity.User;

public interface UserDataAccessInterface {
    void saveUser(User user);
    User getUser(String username);
    void updateUser(User user);
    void deleteUser(String username);
    User getCurrentUser();
    void setCurrentUser(User user);
}
