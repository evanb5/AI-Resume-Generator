// test/entity/CommonCVFactoryTest.java
package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonCVFactoryTest {

    @Test
    public void testCreateCV() {
        CVFactory factory = new CommonCVFactory();
        CV cv = factory.createCV();
        assertNotNull(cv);
        assertTrue(cv instanceof CommonCV);
    }
}
