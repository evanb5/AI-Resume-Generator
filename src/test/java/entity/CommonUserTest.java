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

}
