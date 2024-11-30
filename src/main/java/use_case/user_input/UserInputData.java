// use_case/user_input/UserInputData.java
package use_case.user_input;

import entity.User;

import java.util.List;

public class UserInputData {
    private String fullname;
    private String email;
    private String[] workexperience;
    private String[] education;
    private String[] skills;

    public UserInputData(String fullname, String email, String[] workexperience, String[] education, String[] skills) {
        this.fullname = fullname;
        this.email = email;
        this.workexperience = workexperience;
        this.education = education;
        this.skills = skills;
    }

    public String getFullname() {return fullname;}
    public String getEmail() {return email;}
    public String[] getWorkexperience() {return workexperience;}
    public String[] getEducation() {return education;}
    public String[] getSkills() {return skills;}
}
