// test/entity/CommonResumeTest.java
package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonResumeTest {

    @Test
    public void testGetAndSetResumeName() {
        CommonResume resume = new CommonResume();
        resume.setResumeName("My Resume");
        assertEquals("My Resume", resume.getResumeName());
    }

    @Test
    public void testGetAndSetResumeContent() {
        CommonResume resume = new CommonResume();
        resume.setResumeContent("Content of the resume");
        assertEquals("Content of the resume", resume.getResumeContent());
    }
}
