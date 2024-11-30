// test/use_case/HistoryNumber/HistoryInteractorTest.java
package use_case.HistoryNumber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import data_access.UserDataAccessInterface;
import entity.User;

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

        when(userDataAccess.getCurrentUser()).thenReturn(user);
        interactor = new HistoryInteractor(userDataAccess, presenter);
    }

    @Test
    public void testHistoryInputWithUser() {
        when(user.getnumCV()).thenReturn(2);
        when(user.getnumresume()).thenReturn(3);
        when(user.getnumsuggestion()).thenReturn(1);

        interactor.historyinput();

        verify(presenter).present(any(HistoryOutputData.class));
    }

    @Test
    public void testHistoryInputWithoutUser() {
        when(userDataAccess.getCurrentUser()).thenReturn(null);

        interactor.historyinput();

        verify(presenter).present(any(HistoryOutputData.class));
    }
}
