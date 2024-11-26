package data_access;

import entity.User;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements UserDataAccessInterface {
    private Map<String, User> users;
    private User currentUser;

    public InMemoryUserDataAccessObject() {
        this.users = new HashMap<>();
    }

    @Override
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public User getCurrentUser() {
        return this.currentUser;
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

    // New methods for resumes
    @Override
    public String getResumeContent(User user, int index) {
        if (index >= 0 && index < user.getResume().size()) {
            return user.getResume().get(index);
        }
        return null;
    }

    @Override
    public int getResumeCount(User user) {
        return user.getResume().size();
    }

    @Override
    public void addResume(User user, String resumeContent, String title) {
        user.addResume(resumeContent);
    }

    @Override
    public String getResumeTitle(User user, int index) {
        if (index >= 0 && index < user.getResume().size()) {
            return "Resume " + (index + 1);
        }
        return null;
    }
}
