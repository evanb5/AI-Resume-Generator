package use_case.user_input;

import java.util.List;

public class UserInputOutputDataforrefresh {
    private String fullname;
    private String email;
    private List<String> workexperience;
    private List<String> education;
    private List<String> skills;

    public UserInputOutputDataforrefresh(String fullname, String email, List<String> workexperience, List<String> education, List<String> skills) {
        this.fullname = fullname;
        this.email = email;
        this.workexperience = workexperience;
        this.education = education;
        this.skills = skills;
    }

    public String getFullname() {return fullname;}
    public String getEmail() {return email;}
    public List<String> getWorkexperience() {return workexperience;}
    public List<String> getEducation() {return education;}
    public List<String> getSkills() {return skills;}
}
