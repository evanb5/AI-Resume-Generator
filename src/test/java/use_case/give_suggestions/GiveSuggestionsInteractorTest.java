// test/use_case/give_suggestions/GiveSuggestionsInteractorTest.java
package use_case.give_suggestions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import data_access.UserDataAccessInterface;
import entity.User;
import services.ChatGPTService;

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
    public void testGiveSuggestions() {
        GiveSuggestionsInputData inputData = new GiveSuggestionsInputData("Resume Content", "Job Description");
        when(chatGPTService.generateSuggestions(anyString(), eq("Resume Content"), eq("Job Description")))
                .thenReturn("Generated Suggestions");

        interactor.giveSuggestions(inputData);

        verify(chatGPTService).generateSuggestions(anyString(), eq("Resume Content"), eq("Job Description"));
        verify(user).addsuggestion("Generated Suggestions");
        verify(presenter).present(any(GiveSuggestionsOutputData.class));
    }
}
