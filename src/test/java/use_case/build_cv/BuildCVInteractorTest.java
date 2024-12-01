// test/use_case/build_cv/BuildCVInteractorTest.java
package use_case.build_cv;

import data_access.UserDataAccessInterface;
import entity.CV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ChatGPTService;

import static org.mockito.Mockito.*;

public class BuildCVInteractorTest {

    private BuildCVInteractor interactor;
    private UserDataAccessInterface userDataAccess;
    private BuildCVOutputBoundary presenter;
    private ChatGPTService chatGPTService;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        presenter = mock(BuildCVOutputBoundary.class);
        chatGPTService = mock(ChatGPTService.class);

        interactor = new BuildCVInteractor(userDataAccess, presenter);
        interactor.setChatGPTService(chatGPTService);
    }

    @Test
    public void testBuildCVSuccess() {
        BuildCVInputData inputData = new BuildCVInputData("Job Description", "My CV Title");

        when(userDataAccess.getCurrentUserName()).thenReturn("john_doe");
        when(chatGPTService.generateCV("My CV Title", "Job Description")).thenReturn("Generated CV Content");

        interactor.buildCV(inputData);

        verify(chatGPTService).generateCV("My CV Title", "Job Description");
        verify(userDataAccess).addCv(eq("john_doe"), any(CV.class));
        verify(presenter).present(argThat(outputData ->
                outputData.getFormattedCV().equals("Generated CV Content") &&
                        outputData.getMessage().equals("CV generated successfully")
        ));
    }

    @Test
    public void testBuildCVWithEmptyJobDescription() {
        BuildCVInputData inputData = new BuildCVInputData("", "My CV Title");

        interactor.buildCV(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getFormattedCV().isEmpty() &&
                        outputData.getMessage().equals("Job description is empty")
        ));

        verifyNoInteractions(chatGPTService);
    }

    @Test
    public void testBuildCVWithNullJobDescription() {
        BuildCVInputData inputData = new BuildCVInputData(null, "My CV Title");

        interactor.buildCV(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getFormattedCV().isEmpty() &&
                        outputData.getMessage().equals("Job description is empty")
        ));

        verifyNoInteractions(chatGPTService);
    }
}
