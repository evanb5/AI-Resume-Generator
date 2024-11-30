// test/entity/CommonUserTest.java
package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class CommonUserTest {

    @Test
    public void testGetAndSetUsername() {
        CommonUser user = new CommonUser();
        user.setUsername("john_doe");
        assertEquals("john_doe", user.getUsername());
    }

    @Test
    public void testGetAndSetPassword() {
        CommonUser user = new CommonUser();
        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testGetAndSetEmail() {
        CommonUser user = new CommonUser();
        user.setEmail("john@example.com");
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    public void testGetAndSetFullName() {
        CommonUser user = new CommonUser();
        user.setFullName("John Doe");
        assertEquals("John Doe", user.getFullName());
    }

    @Test
    public void testGetAndSetWorkExperience() {
        CommonUser user = new CommonUser();
        List<String> workExp = Arrays.asList("Software Engineer at Company A", "Developer at Company B");
        user.setWorkExperience(workExp);
        assertEquals(workExp, user.getWorkExperience());
    }

    @Test
    public void testGetAndSetEducation() {
        CommonUser user = new CommonUser();
        List<String> education = Arrays.asList("BSc in Computer Science", "MSc in Software Engineering");
        user.setEducation(education);
        assertEquals(education, user.getEducation());
    }

    @Test
    public void testGetAndSetSkills() {
        CommonUser user = new CommonUser();
        List<String> skills = Arrays.asList("Java", "Python", "SQL");
        user.setSkills(skills);
        assertEquals(skills, user.getSkills());
    }

    @Test
    public void testAddAndGetResume() {
        CommonUser user = new CommonUser();
        user.addResume("Resume Content 1");
        user.addResume("Resume Content 2");
        List<String> expectedResumes = Arrays.asList("Resume Content 1", "Resume Content 2");
        assertEquals(expectedResumes, user.getResumes());
        assertEquals(2, user.getnumresume());
    }

    @Test
    public void testAddAndGetCvs() {
        CommonUser user = new CommonUser();
        user.addCv("CV1", "Content of CV1");
        user.addCv("CV2", "Content of CV2");
        Map<String, String> cvs = user.getCvs();
        assertEquals(2, cvs.size());
        assertEquals("Content of CV1", cvs.get("CV1"));
        assertEquals("Content of CV2", cvs.get("CV2"));
        assertEquals(2, user.getnumCV());
    }

    @Test
    public void testSetAndGetCVIndex() {
        CommonUser user = new CommonUser();
        user.setCVindex(1);
        assertEquals(1, user.getCVindex());
    }

    @Test
    public void testAddSuggestionAndGetNumSuggestion() {
        CommonUser user = new CommonUser();
        user.addsuggestion("Suggestion 1");
        user.addsuggestion("Suggestion 2");
        assertEquals(2, user.getnumsuggestion());
    }

    @Test
    public void testGetResume() {
        CommonUser user = new CommonUser();
        user.addResume("Resume Content 1");
        user.addResume("Resume Content 2");
        List<String> expectedResumes = Arrays.asList("Resume Content 1", "Resume Content 2");
        assertEquals(expectedResumes, user.getResume());
    }
}
