// test/use_case/build_cv/BuildCVInteractorTest.java
package use_case.build_cv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import data_access.UserDataAccessInterface;
import entity.User;
import services.ChatGPTService;

public class BuildCVInteractorTest {

    private BuildCVInteractor interactor;
    private UserDataAccessInterface userDataAccess;
    private BuildCVOutputBoundary presenter;
    private ChatGPTService chatGPTService;
    private User user;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        presenter = mock(BuildCVOutputBoundary.class);
        chatGPTService = mock(ChatGPTService.class);
        user = mock(User.class);

        when(userDataAccess.getCurrentUser()).thenReturn(user);
        interactor = new BuildCVInteractor(userDataAccess, presenter);
        interactor.setChatGPTService(chatGPTService);
    }

    @Test
    public void testBuildCV() {
        BuildCVInputData inputData = new BuildCVInputData("Job Description", "My CV");
        when(chatGPTService.generateCV(anyString(), anyString())).thenReturn("Generated CV Content");

        interactor.buildCV(inputData);

        verify(userDataAccess).getCurrentUser();
        verify(chatGPTService).generateCV(anyString(), eq("Job Description"));
        verify(user).addCv("My CV", "template: Template 1\ncontents of the CV\nGenerated CV Content");
        verify(userDataAccess).updateUser(user);
        verify(presenter).present(any(BuildCVOutputData.class));
    }
}
