package data_access;

import entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements UserDataAccessInterface {
    private Map<String, User> users;
    private Map<String, ArrayList<Resume>> resumes;
    private Map<String, ArrayList<CV>> cvs;
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


    ////TODO: RESUMES - use these to implement
    @Override
    public void addResume(String username, Resume resume) {
        resumes.get(username).add(resume);
    }

    @Override
    public ArrayList<Resume> getResumes(String username) {
        return resumes.get(username);
    }

    // New methods for resumes
    @Override
    public Resume getResumeContent(String username, int index) {
        if (index >= 0 && index < resumes.get(username).size()) {
            return resumes.get(username).get(index);
        }
        return null;
    }

    @Override
    public int getResumeCount(String username) {
        return resumes.get(username).size();
    }


    ////TODO: CVs - use these methods to implement CVs
    @Override
    public void addCv(String username, CV cv) {
        cvs.get(username).add(cv);
    }

    @Override
    public ArrayList<CV> getCvs(String username) {
        return cvs.get(username);
    }

    @Override
    public int getCvCount(String username) {
        return cvs.get(username).size();
    }

    //Two different ways to use getCvContent - use whichever is easier
    @Override
    public CV getCvContent(String username, String cvName) {
        for (CV cv : cvs.get(username)) {
            if (cv.getName().equals(cvName)) {
                return cv;
            }
        }
        return null;
    }

    //Two different ways to use getCvContent - use whichever is easier
    @Override
    public CV getCvContent(String username, int index) {
        if (index >= 0 && index < cvs.get(username).size()) {
            return cvs.get(username).get(index);
        }
        return null;
    }

}
