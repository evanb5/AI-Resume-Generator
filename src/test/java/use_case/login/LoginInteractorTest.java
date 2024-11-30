// test/use_case/login/LoginInteractorTest.java
package use_case.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import data_access.UserDataAccessInterface;
import entity.User;

public class LoginInteractorTest {

    private LoginInteractor interactor;
    private UserDataAccessInterface userDataAccess;
    private LoginOutputBoundary presenter;
    private User user;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        presenter = mock(LoginOutputBoundary.class);
        user = mock(User.class);

        interactor = new LoginInteractor(userDataAccess, presenter);
    }

    @Test
    public void testLoginSuccess() {
        LoginInputData inputData = new LoginInputData("john_doe", "password123");

        when(userDataAccess.getUser("john_doe")).thenReturn(user);
        when(user.getPassword()).thenReturn("password123");

        interactor.login(inputData);

        verify(userDataAccess).setCurrentUser(user);
        verify(presenter).present(any(LoginOutputData.class));
    }

    @Test
    public void testLoginUserNotFound() {
        LoginInputData inputData = new LoginInputData("john_doe", "password123");

        when(userDataAccess.getUser("john_doe")).thenReturn(null);

        interactor.login(inputData);

        verify(presenter).present(any(LoginOutputData.class));
        verify(userDataAccess, never()).setCurrentUser(any());
    }

    @Test
    public void testLoginIncorrectPassword() {
        LoginInputData inputData = new LoginInputData("john_doe", "wrongpassword");

        when(userDataAccess.getUser("john_doe")).thenReturn(user);
        when(user.getPassword()).thenReturn("password123");

        interactor.login(inputData);

        verify(presenter).present(any(LoginOutputData.class));
        verify(userDataAccess, never()).setCurrentUser(any());
    }
}
