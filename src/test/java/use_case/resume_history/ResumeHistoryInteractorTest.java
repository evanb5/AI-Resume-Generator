package use_case.resume_history;

import data_access.UserDataAccessInterface;
import entity.Resume;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ResumeHistoryInteractorTest {

    private ResumeHistoryInteractor interactor;
    private UserDataAccessInterface userDataAccess;
    private ResumeHistoryOutputBoundary presenter;
    private User user;
    private Resume resume;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        presenter = mock(ResumeHistoryOutputBoundary.class);
        user = mock(User.class);
        resume = mock(Resume.class);

        interactor = new ResumeHistoryInteractor(userDataAccess, presenter);
    }

    @Test
    public void testFetchAllResumeTitles() {
        when(userDataAccess.getCurrentUser()).thenReturn(user);
        when(user.getUsername()).thenReturn("john_doe");
        when(userDataAccess.getResumeCount("john_doe")).thenReturn(2);

        ResumeHistoryInputData inputData = new ResumeHistoryInputData(-4);

        interactor.fetchResumeHistory(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getResumes() == 2 &&
                        outputData.getResumeContent().isEmpty() &&
                        outputData.getMessage().equals("Resume titles fetched successfully.")
        ));
    }

    @Test
    public void testFetchSpecificResume() {
        when(userDataAccess.getCurrentUser()).thenReturn(user);
        when(user.getUsername()).thenReturn("john_doe");
        when(userDataAccess.getResumeCount("john_doe")).thenReturn(2);
        when(userDataAccess.getResumeContent("john_doe", 1)).thenReturn(resume);
        when(resume.toString()).thenReturn("Resume Content");

        ResumeHistoryInputData inputData = new ResumeHistoryInputData(1);

        interactor.fetchResumeHistory(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getResumes() == 2 &&
                        outputData.getResumeContent().equals("Resume Content") &&
                        outputData.getMessage().equals("Resume content fetched successfully.")
        ));
    }

    @Test
    public void testFetchResumeWithInvalidIndex() {
        when(userDataAccess.getCurrentUser()).thenReturn(user);
        when(user.getUsername()).thenReturn("john_doe");
        when(userDataAccess.getResumeCount("john_doe")).thenReturn(2);

        ResumeHistoryInputData inputData = new ResumeHistoryInputData(5);

        interactor.fetchResumeHistory(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getResumes() == 2 &&
                        outputData.getResumeContent().isEmpty() &&
                        outputData.getMessage().equals("Invalid resume index.")
        ));
    }

    @Test
    public void testFetchResumeWithoutUser() {
        when(userDataAccess.getCurrentUser()).thenReturn(null);

        ResumeHistoryInputData inputData = new ResumeHistoryInputData(0);

        interactor.fetchResumeHistory(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getResumes() == 0 &&
                        outputData.getResumeContent().isEmpty() &&
                        outputData.getMessage().equals("No user is currently logged in.")
        ));
    }

    @Test
    public void testFetchResumeWithNegativeIndex() {
        // User exists
        when(userDataAccess.getCurrentUser()).thenReturn(user);
        when(user.getUsername()).thenReturn("john_doe");
        when(userDataAccess.getResumeCount("john_doe")).thenReturn(2);

        // Invalid negative index (not -4)
        ResumeHistoryInputData inputData = new ResumeHistoryInputData(-1);

        interactor.fetchResumeHistory(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getResumes() == 2 &&
                        outputData.getResumeContent().isEmpty() &&
                        outputData.getMessage().equals("Invalid resume index.")
        ));
    }
}
