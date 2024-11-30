// test/use_case/user_input/UserInputOutputDataTest.java
package use_case.user_input;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserInputOutputDataTest {

    @Test
    public void testConstructorAndGetter() {
        // Create an instance with success = true
        UserInputOutputData outputData = new UserInputOutputData(true);

        // Verify the value of success
        assertTrue(outputData.isSuccess());

        // Create another instance with success = false
        UserInputOutputData outputDataFalse = new UserInputOutputData(false);

        // Verify the value of success
        assertFalse(outputDataFalse.isSuccess());
    }
}
