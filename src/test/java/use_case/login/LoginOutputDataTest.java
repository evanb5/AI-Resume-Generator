// test/use_case/login/LoginOutputDataTest.java
package use_case.login;

import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginOutputDataTest {

    @Test
    public void testLoginOutputData() {
        // Arrange
        boolean success = true;
        String message = "Login successful";
        User mockUser = mock(User.class); // Mocking the User object

        // Act
        LoginOutputData outputData = new LoginOutputData(success, message, mockUser);

        // Assert
        assertTrue(outputData.isSuccess());
        assertEquals(message, outputData.getMessage());
        assertEquals(mockUser, outputData.getUser());
    }

    @Test
    public void testLoginOutputDataFailure() {
        // Arrange
        boolean success = false;
        String message = "Login failed";
        User mockUser = null;

        // Act
        LoginOutputData outputData = new LoginOutputData(success, message, mockUser);

        // Assert
        assertFalse(outputData.isSuccess());
        assertEquals(message, outputData.getMessage());
        assertNull(outputData.getUser());
    }
}
