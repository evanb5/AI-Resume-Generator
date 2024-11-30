package use_case.HistoryNumber;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HistoryOutputDataTest {

    @Test
    public void testHistoryOutputDataConstructorAndGetters() {
        // Arrange: Create an instance of HistoryOutputData with specific values
        int cv = 5;
        int resume = 10;
        int suggestion = 3;

        // Act: Initialize the object
        HistoryOutputData historyOutputData = new HistoryOutputData(cv, resume, suggestion);

        // Assert: Verify that the getters return the expected values
        assertEquals(cv, historyOutputData.getCv());
        assertEquals(resume, historyOutputData.getResume());
        assertEquals(suggestion, historyOutputData.getSuggestion());
    }
}
