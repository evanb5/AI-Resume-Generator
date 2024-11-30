// test/use_case/resume_history/ResumeHistoryInteractorTest.java
package use_case.resume_history;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import data_access.UserDataAccessInterface;
import entity.User;

import java.util.Arrays;
import java.util.List;

public class ResumeHistoryInteractorTest {

    private ResumeHistoryInteractor interactor;
    private UserDataAccessInterface userDataAccess;
    private ResumeHistoryOutputBoundary presenter;
    private User user;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        presenter = mock(ResumeHistoryOutputBoundary.class);
        user = mock(User.class);

        interactor = new ResumeHistoryInteractor(userDataAccess, presenter);
    }

    @Test
    public void testFetchAllResumeTitles() {
        when(userDataAccess.getCurrentUser()).thenReturn(user);
        when(user.getResumes()).thenReturn(Arrays.asList("Resume1", "Resume2"));

        ResumeHistoryInputData inputData = new ResumeHistoryInputData(-4);

        interactor.fetchResumeHistory(inputData);

        verify(presenter).present(any(ResumeHistoryOutputData.class));
    }

    @Test
    public void testFetchSpecificResume() {
        when(userDataAccess.getCurrentUser()).thenReturn(user);
        when(user.getResumes()).thenReturn(Arrays.asList("Resume1", "Resume2"));

        ResumeHistoryInputData inputData = new ResumeHistoryInputData(1);

        interactor.fetchResumeHistory(inputData);

        verify(presenter).present(any(ResumeHistoryOutputData.class));
    }

    @Test
    public void testFetchResumeWithInvalidIndex() {
        when(userDataAccess.getCurrentUser()).thenReturn(user);
        when(user.getResumes()).thenReturn(Arrays.asList("Resume1", "Resume2"));

        ResumeHistoryInputData inputData = new ResumeHistoryInputData(5);

        interactor.fetchResumeHistory(inputData);

        verify(presenter).present(any(ResumeHistoryOutputData.class));
    }

    @Test
    public void testFetchResumeWithoutUser() {
        when(userDataAccess.getCurrentUser()).thenReturn(null);

        ResumeHistoryInputData inputData = new ResumeHistoryInputData(0);

        interactor.fetchResumeHistory(inputData);

        verify(presenter).present(any(ResumeHistoryOutputData.class));
    }
}
