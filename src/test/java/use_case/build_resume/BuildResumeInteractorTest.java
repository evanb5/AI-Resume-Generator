// test/use_case/build_resume/BuildResumeInteractorTest.java
package use_case.build_resume;

import entity.ResumeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import data_access.UserDataAccessInterface;
import entity.User;
import services.ChatGPTService;

import java.util.List;

public class BuildResumeInteractorTest {

    private BuildResumeInteractor interactor;
    private UserDataAccessInterface userDataAccess;
    private BuildResumeOutputBoundary presenter;
    private ChatGPTService chatGPTService;
    private User user;
    private ResumeFactory resumeFactory;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        presenter = mock(BuildResumeOutputBoundary.class);
        chatGPTService = mock(ChatGPTService.class);
        user = mock(User.class);
        resumeFactory = mock(ResumeFactory.class);

        when(user.getUsername()).thenReturn("testUser");
        when(user.getFullName()).thenReturn("Test User");
        when(user.getEmail()).thenReturn("test@example.com");
        when(user.getWorkExperience()).thenReturn(List.of("Software Developer at XYZ Corp"));
        when(user.getEducation()).thenReturn(List.of("B.Sc. in Computer Science"));
        when(user.getSkills()).thenReturn(List.of("Java", "Python"));

        when(userDataAccess.getCurrentUser()).thenReturn(user);

        interactor = new BuildResumeInteractor(userDataAccess, presenter, resumeFactory);
        interactor.setChatGPTService(chatGPTService);
    }

    @Test
    public void testBuildResumeWithValidJobDescription() {
        BuildResumeInputData inputData = new BuildResumeInputData(user.getUsername(), generateUserInfo(user), "Job Description", 1);
        when(chatGPTService.generateResume(anyString(), eq("Job Description"), eq(1)))
                .thenReturn("Generated Resume Content");

        interactor.buildResume(inputData);

        verify(chatGPTService).generateResume(anyString(), eq("Job Description"), eq(1));
        verify(userDataAccess).addResume(argThat(resume ->
                        resume.getResumeContent().equals("Generated Resume Content") &&
                                resume.getResumeName().equals("Generated using Template 1")),
                eq("testUser")
        );
        verify(presenter).present(argThat(output ->
                output.getFormattedResume().equals("Generated Resume Content") &&
                        output.getMessage().equals("Resume generated successfully")));
    }

    @Test
    public void testBuildResumeWithEmptyJobDescription() {
        BuildResumeInputData inputData = new BuildResumeInputData(user.getUsername(), generateUserInfo(user), "", 1);

        interactor.buildResume(inputData);

        verify(presenter).present(argThat(output ->
                output.getFormattedResume().isEmpty() &&
                        output.getMessage().equals("Job description is empty")));
        verifyNoInteractions(chatGPTService);
        verify(user, never()).addResume(anyString());
    }

    @Test
    public void testBuildResumeWithNullJobDescription() {
        BuildResumeInputData inputData = new BuildResumeInputData(user.getUsername(), generateUserInfo(user), null, 1);

        interactor.buildResume(inputData);

        verify(presenter).present(argThat(output ->
                output.getFormattedResume().isEmpty() &&
                        output.getMessage().equals("Job description is empty")));
        verifyNoInteractions(chatGPTService);
        verify(user, never()).addResume(anyString());
    }

    private String generateUserInfo(User user) {
        return "Name: " + user.getFullName() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "Work Experience: " + String.join(", ", user.getWorkExperience()) + "\n" +
                "Education: " + String.join(", ", user.getEducation()) + "\n" +
                "Skills: " + String.join(", ", user.getSkills());
    }
}
