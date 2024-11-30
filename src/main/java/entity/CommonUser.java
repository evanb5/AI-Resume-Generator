// entity/CommonUser.java
package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUser implements User {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private List<String> workExperience;
    private List<String> education;
    private List<String> skills;
    private List<String> resumeS;
    private List<String> resumeTitles;
    private Map<String,String> storedCv;
    private List<String> suggestions;
    private int CVindex;
  
    public CommonUser() {
        this.workExperience = new ArrayList<>();
        this.education = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.resumeS = new ArrayList<>();
        this.storedCv = new HashMap<>();
        this.suggestions = new ArrayList<>();
    }

    @Override
    public void addsuggestion(String suggestion) {this.suggestions.add(suggestion);}

    @Override
    public int getnumsuggestion() {return suggestions.size();}

    @Override
    public int getnumCV(){ return storedCv.size();}

    @Override
    public int getCVindex() {return CVindex;}

    @Override
    public void setCVindex(int index){this.CVindex = index;}

    @Override
    public List<String> getResume(){return resumeS;}

    @Override
    public List<String> getResumes() {
        // Return the list of all resume content
        return resumeS;
    }

    @Override
    public void addResume(String resume) {
        this.resumeS.add(resume); // Add the resume content
    }

    @Override

    public int getnumresume(){return this.resumeS.size();}

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public List<String> getWorkExperience() {return workExperience;}

    @Override
    public void setWorkExperience(List<String> workExperience) {
        this.workExperience = workExperience;
    }

    @Override
    public List<String> getEducation() {return education;}

    @Override
    public void setEducation(List<String> education) {
        this.education = education;
    }

    @Override
    public List<String> getSkills() {
        return skills;
    }

    @Override
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public Map<String, String> getCvs() {
        return storedCv;
    }

    @Override
    public void addCv(String cvName, String cv) {
        this.storedCv.put(cvName, cv);
    }
}
