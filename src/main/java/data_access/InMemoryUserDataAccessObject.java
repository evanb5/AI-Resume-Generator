package data_access;

import entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements UserDataAccessInterface {
    private Map<String, User> users;
    private Map<String, ArrayList<Resume>> resumes;
    private Map<String, HashMap<String,String>> cvs;
    private User currentUser;

    public InMemoryUserDataAccessObject() {
        this.users = new HashMap<>();
        this.resumes = new HashMap<>();
        this.cvs = new HashMap<>();
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
    public String getCurrentUserName() {
        return this.currentUser.getUsername();
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
        resumes.remove(username);
    }


    @Override
    public void addResume(Resume resume, String username) {
        resumes.computeIfAbsent(username, k -> new ArrayList<>()).add(resume);
    }

    @Override
    public ArrayList<Resume> getResumes(String username) {
        return resumes.getOrDefault(username, new ArrayList<>());
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
        return resumes.getOrDefault(username, new ArrayList<>()).size();
    }


    ////TODO: CVs - use these methods to implement CVs
    @Override
    public void addCv(String username, CV cv) {
        cvs.computeIfAbsent(username, k -> new HashMap<>());
        cvs.get(username).put(cv.getName(), cv.getCv());
    }

    @Override
    public HashMap<String, String> getCvs(String username) {
        return cvs.get(username);
    }

    @Override
    public int getCvCount(String username) {
        return cvs.getOrDefault(username, new HashMap<>()).size();
    }

    //Two different ways to use getCvContent - use whichever is easier
    @Override
    public String getCvContent(String username, String cvName) {
        return cvs.get(username).get(cvName);
    }
}
