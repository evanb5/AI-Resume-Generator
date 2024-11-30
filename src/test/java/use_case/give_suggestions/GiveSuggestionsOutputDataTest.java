// test/use_case/give_suggestions/GiveSuggestionsOutputDataTest.java
package use_case.give_suggestions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GiveSuggestionsOutputDataTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String expectedSuggestions = "These are some suggestions";
        String expectedMessage = "Suggestions generated successfully";

        // Act
        GiveSuggestionsOutputData outputData = new GiveSuggestionsOutputData(expectedSuggestions, expectedMessage);

        // Assert
        assertEquals(expectedSuggestions, outputData.getSuggestions());
        assertEquals(expectedMessage, outputData.getMessage());
    }
}
