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
    private Map<String,String> storedCv;
    public List<String> suggestions;
  
    public CommonUser() {
        this.workExperience = new ArrayList<>();
        this.education = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.resumeS = new ArrayList<>();
        this.storedCv = new HashMap<>();
        this.suggestions = new ArrayList<>();
    }
    @Override
    public List<String> getsuggestions(){return suggestions;}

    @Override
    public void addsuggestion(String suggestion) {this.suggestions.add(suggestion);}

    @Override
    public int getnumsuggestion() {return suggestions.size();}

    @Override
    public int getnumCV(){ return storedCv.size(); }

    @Override
    public List<String> getResume(){return resumeS;}

    @Override
    public void addResume(String resume){this.resumeS.add(resume);}

    @Override
    public int getnumresume(){return resumeS.size();}

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
    public List<String> getWorkExperience() {
        System.out.println("work experience obtained");
        System.out.println(workExperience);
        return workExperience;
    }

    @Override
    public void setWorkExperience(List<String> workExperience) {
        this.workExperience = workExperience;
    }

    @Override
    public List<String> getEducation() {
        return education;
    }

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

    @Override
    public void removeCv(String cvName) {
        this.storedCv.remove(cvName);
    }

}
