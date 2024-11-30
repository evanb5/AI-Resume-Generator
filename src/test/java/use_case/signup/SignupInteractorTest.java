// test/use_case/signup/SignupInteractorTest.java
package use_case.signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import data_access.UserDataAccessInterface;
import entity.*;

public class SignupInteractorTest {

    private SignupInteractor interactor;
    private UserDataAccessInterface userDataAccess;
    private UserFactory userFactory;
    private SignupOutputBoundary presenter;
    private User user;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        userFactory = mock(UserFactory.class);
        presenter = mock(SignupOutputBoundary.class);
        user = mock(User.class);

        interactor = new SignupInteractor(userDataAccess, userFactory, presenter);
    }

    @Test
    public void testSignupSuccess() {
        SignupInputData inputData = new SignupInputData("john_doe", "password123", "john@example.com");

        when(userDataAccess.getUser("john_doe")).thenReturn(null);
        when(userFactory.createUser()).thenReturn(user);

        interactor.signup(inputData);

        verify(user).setUsername("john_doe");
        verify(user).setPassword("password123");
        verify(user).setEmail("john@example.com");
        verify(userDataAccess).saveUser(user);
        verify(presenter).present(any(SignupOutputData.class));
    }

    @Test
    public void testSignupUsernameExists() {
        SignupInputData inputData = new SignupInputData("john_doe", "password123", "john@example.com");

        when(userDataAccess.getUser("john_doe")).thenReturn(user);

        interactor.signup(inputData);

        verify(presenter).present(any(SignupOutputData.class));
        verify(userDataAccess, never()).saveUser(any());
    }
}
