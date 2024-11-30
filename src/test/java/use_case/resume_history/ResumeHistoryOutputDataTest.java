// test/use_case/resume_history/ResumeHistoryOutputDataTest.java
package use_case.resume_history;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResumeHistoryOutputDataTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        int resumes = 5;
        String resumeContent = "Sample resume content";
        String message = "Success";

        // Act
        ResumeHistoryOutputData outputData = new ResumeHistoryOutputData(resumes, resumeContent, message);

        // Assert
        assertEquals(resumes, outputData.getResumes());
        assertEquals(resumeContent, outputData.getResumeContent());
        assertEquals(message, outputData.getMessage());
    }
}
