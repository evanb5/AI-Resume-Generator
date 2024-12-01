package interface_adapter.user_input;

import java.util.List;

public class InputUserState {
    private boolean success;
    private String fullname;
    private String email;
    private List<String> workexperience;
    private List<String> education;
    private List<String> skills;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFullname() { return fullname; }

    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<String> getWorkexperience() { return workexperience; }

    public void setWorkexperience(List<String> workexperience) { this.workexperience = workexperience; }

    public List<String> getEducation() { return education; }

    public void setEducation(List<String> education) { this.education = education; }

    public List<String> getSkills() { return skills; }

    public void setSkills(List<String> skills) { this.skills = skills; }
}
