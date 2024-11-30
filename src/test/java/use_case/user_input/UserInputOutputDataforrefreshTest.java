// test/use_case/user_input/UserInputOutputDataforrefreshTest.java
package use_case.user_input;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UserInputOutputDataforrefreshTest {

    @Test
    public void testConstructorAndGetters() {
        // Input data for the constructor
        String fullname = "John Doe";
        String email = "john@example.com";
        List<String> workexperience = Arrays.asList("Company A", "Company B");
        List<String> education = Arrays.asList("University X", "University Y");
        List<String> skills = Arrays.asList("Java", "Python");

        // Create an instance
        UserInputOutputDataforrefresh outputData = new UserInputOutputDataforrefresh(
                fullname, email, workexperience, education, skills
        );

        // Verify the values of all fields
        assertEquals(fullname, outputData.getFullname());
        assertEquals(email, outputData.getEmail());
        assertEquals(workexperience, outputData.getWorkexperience());
        assertEquals(education, outputData.getEducation());
        assertEquals(skills, outputData.getSkills());
    }
}
