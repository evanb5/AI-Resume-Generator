// test/use_case/resume_history/ResumeHistoryInputDataTest.java
package use_case.resume_history;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResumeHistoryInputDataTest {

    @Test
    public void testConstructorAndGetIndex() {
        // Arrange
        int index = -4;

        // Act
        ResumeHistoryInputData inputData = new ResumeHistoryInputData(index);

        // Assert
        assertEquals(index, inputData.getIndex());
    }

    @Test
    public void testSetIndex() {
        // Arrange
        ResumeHistoryInputData inputData = new ResumeHistoryInputData(0);

        // Act
        inputData.setIndex(-2);

        // Assert
        assertEquals(-2, inputData.getIndex());
    }
}
