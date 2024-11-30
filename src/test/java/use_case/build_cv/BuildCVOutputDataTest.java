// test/use_case/build_cv/BuildCVOutputDataTest.java
package use_case.build_cv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BuildCVOutputDataTest {

    @Test
    public void testGetFormattedCV() {
        BuildCVOutputData outputData = new BuildCVOutputData("Formatted CV Content", "Success Message");
        assertEquals("Formatted CV Content", outputData.getFormattedCV());
    }

    @Test
    public void testGetMessage() {
        BuildCVOutputData outputData = new BuildCVOutputData("Formatted CV Content", "Success Message");
        assertEquals("Success Message", outputData.getMessage());
    }
}
