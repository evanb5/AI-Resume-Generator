package data_access;

import entity.User;

public interface UserDataAccessInterface {
    void saveUser(User user);
    User getUser(String username);
    void updateUser(User user);
    void deleteUser(String username);
    User getCurrentUser();
    void setCurrentUser(User user);

    // New methods for resumes
    String getResumeContent(User user, int index);
    int getResumeCount(User user);
    void addResume(User user, String resumeContent, String title);
    String getResumeTitle(User user, int index);
}
