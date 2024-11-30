// test/use_case/signup/SignupOutputDataTest.java
package use_case.signup;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SignupOutputDataTest {

    @Test
    public void testSignupOutputDataConstructorAndGetters() {
        // Arrange: Create a SignupOutputData object with test values
        boolean expectedSuccess = true;
        String expectedMessage = "Signup successful";
        String expectedUsername = "john_doe";

        SignupOutputData outputData = new SignupOutputData(expectedSuccess, expectedMessage, expectedUsername);

        // Assert: Verify that the getters return the correct values
        assertEquals(expectedSuccess, outputData.isSuccess());
        assertEquals(expectedMessage, outputData.getMessage());
        assertEquals(expectedUsername, outputData.getUsername());
    }
}
