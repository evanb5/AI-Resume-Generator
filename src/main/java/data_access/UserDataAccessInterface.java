package data_access;

import entity.Resume;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserDataAccessInterface {
    void saveUser(User user);
    User getUser(String username);
    void updateUser(User user);
    void deleteUser(String username);
    User getCurrentUser();
    void setCurrentUser(User user);

    void addResume(Resume resume, String username);
    ArrayList<Resume> getResumes(String username);
    // New methods for resumes
    Resume getResumeContent(String username, int index);
    int getResumeCount(String username);


}
