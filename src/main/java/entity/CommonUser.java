// entity/CommonUser.java
package entity;

import java.util.ArrayList;
import java.util.List;

public class CommonUser implements User {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String currentusername;
    private List<String> workExperience;
    private List<String> education;
    private List<String> skills;
    private List<String> resume;

    public CommonUser() {
        this.workExperience = new ArrayList<>();
        this.education = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.resume = new ArrayList<>();
    }

    public List<String> getResume(){return resume;}
    public void addResume(String resume){this.resume.add(resume);}

    @Override
    public int getnumresume(){return resume.size();}

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
}
