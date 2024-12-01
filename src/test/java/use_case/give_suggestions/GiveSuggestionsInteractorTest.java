// test/use_case/give_suggestions/GiveSuggestionsInteractorTest.java
package use_case.give_suggestions;

import data_access.UserDataAccessInterface;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ChatGPTService;

import static org.mockito.Mockito.*;

public class GiveSuggestionsInteractorTest {

    private GiveSuggestionsInteractor interactor;
    private UserDataAccessInterface userDataAccess;
    private GiveSuggestionsOutputBoundary presenter;
    private ChatGPTService chatGPTService;
    private User user;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        presenter = mock(GiveSuggestionsOutputBoundary.class);
        chatGPTService = mock(ChatGPTService.class);
        user = mock(User.class);

        when(userDataAccess.getCurrentUser()).thenReturn(user);

        interactor = new GiveSuggestionsInteractor(userDataAccess, presenter);
        interactor.setChatGPTService(chatGPTService);
    }

    @Test
    public void testGiveSuggestionsSuccess() {
        GiveSuggestionsInputData inputData = new GiveSuggestionsInputData("Resume Content", "Job Description");

        when(chatGPTService.generateSuggestions(anyString(), eq("Resume Content"), eq("Job Description")))
                .thenReturn("Generated Suggestions");
        when(user.getFullName()).thenReturn("John Doe");
        when(user.getEmail()).thenReturn("john@example.com");
        when(user.getWorkExperience()).thenReturn(java.util.Arrays.asList("Company A"));
        when(user.getEducation()).thenReturn(java.util.Arrays.asList("University X"));
        when(user.getSkills()).thenReturn(java.util.Arrays.asList("Java"));

        interactor.giveSuggestions(inputData);

        verify(chatGPTService).generateSuggestions(anyString(), eq("Resume Content"), eq("Job Description"));
        verify(presenter).present(argThat(outputData ->
                outputData.getSuggestions().equals("Generated Suggestions") &&
                        outputData.getMessage().equals("Suggestions generated successfully")
        ));
    }
}
