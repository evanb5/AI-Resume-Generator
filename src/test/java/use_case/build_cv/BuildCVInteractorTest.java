package use_case.build_cv;

import data_access.UserDataAccessInterface;
import entity.CV;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import services.ChatGPTService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        User mockUser = mock(User.class);

        when(userDataAccess.getCurrentUserName()).thenReturn("john_doe");
        when(userDataAccess.getUser("john_doe")).thenReturn(mockUser);
        when(mockUser.getFullName()).thenReturn("John Doe");
        when(mockUser.getEmail()).thenReturn("john.doe@example.com");
        when(mockUser.getWorkExperience()).thenReturn(List.of("Developer at X", "Engineer at Y"));
        when(mockUser.getEducation()).thenReturn(List.of("B.Sc. Computer Science"));
        when(mockUser.getSkills()).thenReturn(List.of("Java", "Spring"));

        when(chatGPTService.generateCV(anyString(), eq("Job Description"))).thenReturn("Generated CV Content");

        interactor.buildCV(inputData);

        verify(chatGPTService).generateCV(anyString(), eq("Job Description"));
        verify(userDataAccess).addCv(eq("john_doe"), any(CV.class));
        verify(presenter).present(argThat(outputData ->
                outputData.getFormattedCV().equals("Generated CV Content") &&
                        outputData.getMessage().equals("Cover Letter generated successfully")
        ));
    }

    @Test
    public void testBuildCVWithEmptyJobDescription() {
        BuildCVInputData inputData = new BuildCVInputData("", "My CV Title");

        interactor.buildCV(inputData);

        ArgumentCaptor<BuildCVOutputData> captor = ArgumentCaptor.forClass(BuildCVOutputData.class);
        verify(presenter).present(captor.capture());

        BuildCVOutputData capturedOutput = captor.getValue();
        assertEquals("", capturedOutput.getFormattedCV());
        assertEquals("Job description is empty", capturedOutput.getMessage());

        verifyNoInteractions(chatGPTService);
    }

    @Test
    public void testBuildCVWithNullJobDescription() {
        BuildCVInputData inputData = new BuildCVInputData(null, "My CV Title");

        interactor.buildCV(inputData);

        ArgumentCaptor<BuildCVOutputData> captor = ArgumentCaptor.forClass(BuildCVOutputData.class);
        verify(presenter).present(captor.capture());

        BuildCVOutputData capturedOutput = captor.getValue();
        assertEquals("", capturedOutput.getFormattedCV());
        assertEquals("Job description is empty", capturedOutput.getMessage());

        verifyNoInteractions(chatGPTService);
    }

    @Test
    public void testBuildCVWithNullUser() {
        BuildCVInputData inputData = new BuildCVInputData("Job Description", "My CV Title");

        when(userDataAccess.getCurrentUserName()).thenReturn("john_doe");
        when(userDataAccess.getUser("john_doe")).thenReturn(null);

        interactor.buildCV(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getFormattedCV().isEmpty() &&
                        outputData.getMessage().equals("User not found")
        ));

        verifyNoInteractions(chatGPTService);
    }
}
