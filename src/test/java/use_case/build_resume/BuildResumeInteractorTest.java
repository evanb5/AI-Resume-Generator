// test/use_case/build_resume/BuildResumeInteractorTest.java
package use_case.build_resume;

import data_access.UserDataAccessInterface;
import entity.Resume;
import entity.ResumeFactory;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ChatGPTService;

import java.util.List;

import static org.mockito.Mockito.*;

public class BuildResumeInteractorTest {

    private BuildResumeInteractor interactor;
    private UserDataAccessInterface userDataAccess;
    private BuildResumeOutputBoundary presenter;
    private ChatGPTService chatGPTService;
    private ResumeFactory resumeFactory;
    private User user;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        presenter = mock(BuildResumeOutputBoundary.class);
        chatGPTService = mock(ChatGPTService.class);
        resumeFactory = mock(ResumeFactory.class);
        user = mock(User.class);

        when(userDataAccess.getCurrentUser()).thenReturn(user);

        interactor = new BuildResumeInteractor(userDataAccess, presenter, resumeFactory);
        interactor.setChatGPTService(chatGPTService);
    }

    @Test
    public void testBuildResumeSuccess() {
        BuildResumeInputData inputData = new BuildResumeInputData("Job Description", 1);

        // Mock user data
        when(user.getFullName()).thenReturn("John Doe");
        when(user.getEmail()).thenReturn("john.doe@example.com");
        when(user.getWorkExperience()).thenReturn(List.of("Developer at X", "Engineer at Y"));
        when(user.getEducation()).thenReturn(List.of("B.Sc. in Computer Science"));
        when(user.getSkills()).thenReturn(List.of("Java", "Spring"));

        // Mock other dependencies
        when(chatGPTService.generateResume(anyString(), eq("Job Description"), eq(1)))
                .thenReturn("Generated Resume Content");
        Resume resume = mock(Resume.class);
        when(resumeFactory.createResume()).thenReturn(resume);
        when(user.getUsername()).thenReturn("john_doe");

        // Call the method
        interactor.buildResume(inputData);

        // Verify interactions
        verify(chatGPTService).generateResume(
                argThat(userInfo -> userInfo.contains("John Doe") &&
                        userInfo.contains("john.doe@example.com") &&
                        userInfo.contains("Developer at X, Engineer at Y") &&
                        userInfo.contains("B.Sc. in Computer Science") &&
                        userInfo.contains("Java, Spring")),
                eq("Job Description"),
                eq(1)
        );
        verify(resume).setResumeName("Generated using Template 1");
        verify(resume).setResumeContent("Generated Resume Content");
        verify(userDataAccess).addResume(resume, "john_doe");
        verify(presenter).present(argThat(outputData ->
                outputData.getFormattedResume().equals("Generated Resume Content") &&
                        outputData.getMessage().equals("Resume generated successfully")
        ));
    }


    @Test
    public void testBuildResumeWithEmptyJobDescription() {
        BuildResumeInputData inputData = new BuildResumeInputData("", 1);

        interactor.buildResume(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getFormattedResume().isEmpty() &&
                        outputData.getMessage().equals("Job description is empty")
        ));

        verifyNoInteractions(chatGPTService);
    }

    @Test
    public void testBuildResumeWithNullJobDescription() {
        BuildResumeInputData inputData = new BuildResumeInputData(null, 1);

        interactor.buildResume(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getFormattedResume().isEmpty() &&
                        outputData.getMessage().equals("Job description is empty")
        ));

        verifyNoInteractions(chatGPTService);
    }

    @Test
    public void testBuildResumeWithWhitespaceJobDescription() {
        BuildResumeInputData inputData = new BuildResumeInputData("   ", 1);

        interactor.buildResume(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getFormattedResume().isEmpty() &&
                        outputData.getMessage().equals("Job description is empty")
        ));

        verifyNoInteractions(chatGPTService);
    }
}
