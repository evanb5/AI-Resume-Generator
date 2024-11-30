// test/use_case/build_resume/BuildResumeInteractorTest.java
package use_case.build_resume;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import data_access.UserDataAccessInterface;
import entity.User;
import services.ChatGPTService;

public class BuildResumeInteractorTest {

    private BuildResumeInteractor interactor;
    private UserDataAccessInterface userDataAccess;
    private BuildResumeOutputBoundary presenter;
    private ChatGPTService chatGPTService;
    private User user;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        presenter = mock(BuildResumeOutputBoundary.class);
        chatGPTService = mock(ChatGPTService.class);
        user = mock(User.class);

        when(userDataAccess.getCurrentUser()).thenReturn(user);
        interactor = new BuildResumeInteractor(userDataAccess, presenter);
        interactor.setChatGPTService(chatGPTService);
    }

    @Test
    public void testBuildResumeWithValidJobDescription() {
        BuildResumeInputData inputData = new BuildResumeInputData("Job Description", 1);
        when(chatGPTService.generateResume(anyString(), eq("Job Description"), eq(1)))
                .thenReturn("Generated Resume Content");

        interactor.buildResume(inputData);

        verify(chatGPTService).generateResume(anyString(), eq("Job Description"), eq(1));
        verify(user).addResume("Generated Resume Content");
        verify(presenter).present(any(BuildResumeOutputData.class));
    }

    @Test
    public void testBuildResumeWithEmptyJobDescription() {
        BuildResumeInputData inputData = new BuildResumeInputData("", 1);

        interactor.buildResume(inputData);

        verify(presenter).present(argThat(output ->
                output.getFormattedResume().isEmpty() && output.getMessage().equals("Job description is empty")));
        verifyNoInteractions(chatGPTService);
        verify(user, never()).addResume(anyString());
    }

    @Test
    public void testBuildResumeWithNullJobDescription() {
        BuildResumeInputData inputData = new BuildResumeInputData(null, 1);

        interactor.buildResume(inputData);

        verify(presenter).present(argThat(output ->
                output.getFormattedResume().isEmpty() && output.getMessage().equals("Job description is empty")));
        verifyNoInteractions(chatGPTService);
        verify(user, never()).addResume(anyString());
    }
}
