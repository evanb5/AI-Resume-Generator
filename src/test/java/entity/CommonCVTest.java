// test/entity/CommonCVTest.java
package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonCVTest {

    @Test
    public void testGetAndSetName() {
        CommonCV cv = new CommonCV();
        cv.setName("My CV");
        assertEquals("My CV", cv.getName());
    }

    @Test
    public void testGetAndSetCvContent() {
        CommonCV cv = new CommonCV();
        cv.setCv("Content of the CV");
        assertEquals("Content of the CV", cv.getCv());
    }
}
