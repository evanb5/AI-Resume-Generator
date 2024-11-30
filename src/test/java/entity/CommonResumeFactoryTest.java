// test/entity/CommonResumeFactoryTest.java
package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonResumeFactoryTest {

    @Test
    public void testCreateResume() {
        ResumeFactory factory = new CommonResumeFactory();
        Resume resume = factory.createResume();
        assertNotNull(resume);
        assertTrue(resume instanceof CommonResume);
    }
}
