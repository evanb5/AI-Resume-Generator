// entity/User.java
package entity;

import java.util.List;
import java.util.Map;

public interface User {
    String getUsername();
    void setUsername(String username);

    String getPassword();
    void setPassword(String password);

    String getEmail();
    void setEmail(String email);

    String getFullName();
    void setFullName(String fullName);

    List<String> getWorkExperience();
    void setWorkExperience(List<String> workExperience);

    List<String> getEducation();
    void setEducation(List<String> education);

    List<String> getSkills();
    void setSkills(List<String> skills);

    List<String> getResume();
    void addResume(String resume);
    int getnumresume();
  
    Map<String, String> getCvs();
    void addCv(String cvName, String cv);
    int getnumCV();
    void setCVindex(int index);
    int getCVindex();

    void addsuggestion(String suggestion);
    int getnumsuggestion();


    List<String> getResumes();


}
