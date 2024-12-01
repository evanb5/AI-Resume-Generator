// test/use_case/HistoryNumber/HistoryInteractorTest.java
package use_case.HistoryNumber;

import data_access.UserDataAccessInterface;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class HistoryInteractorTest {

    private HistoryInteractor interactor;
    private UserDataAccessInterface userDataAccess;
    private HistoryOutputBoundary presenter;
    private User user;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        presenter = mock(HistoryOutputBoundary.class);
        user = mock(User.class);

        interactor = new HistoryInteractor(userDataAccess, presenter);
    }

    @Test
    public void testHistoryInputWithUser() {
        when(userDataAccess.getCurrentUser()).thenReturn(user);
        when(user.getUsername()).thenReturn("john_doe");
        when(userDataAccess.getCvCount("john_doe")).thenReturn(2);
        when(userDataAccess.getResumeCount("john_doe")).thenReturn(3);

        interactor.historyinput();

        verify(presenter).present(argThat(outputData ->
                outputData.getCv() == 2 &&
                        outputData.getResume() == 3
        ));
    }

    @Test
    public void testHistoryInputWithoutUser() {
        when(userDataAccess.getCurrentUser()).thenReturn(null);

        interactor.historyinput();

        verify(presenter).present(argThat(outputData ->
                outputData.getCv() == 0 &&
                        outputData.getResume() == 0
        ));
    }
}
