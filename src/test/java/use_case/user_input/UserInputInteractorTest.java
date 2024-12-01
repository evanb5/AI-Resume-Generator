package use_case.user_input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import data_access.UserDataAccessInterface;
import entity.User;

import java.util.Arrays;

public class UserInputInteractorTest {

    private UserInputInteractor interactor;
    private UserDataAccessInterface userDataAccess;
    private UserInputOutputBoundary presenter;
    private User user;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        presenter = mock(UserInputOutputBoundary.class);
        user = mock(User.class);

        interactor = new UserInputInteractor(userDataAccess, presenter);
    }

    @Test
    public void testUpdateUserData() {
        String[] workExp = {"Company A", "Company B"};
        String[] education = {"University X", "University Y"};
        String[] skills = {"Java", "Python"};

        UserInputData inputData = new UserInputData("John Doe", "john@example.com", workExp, education, skills);

        when(userDataAccess.getCurrentUser()).thenReturn(user);

        interactor.updateUserData(inputData);

        verify(user).setFullName("John Doe");
        verify(user).setEmail("john@example.com");
        verify(user).setWorkExperience(Arrays.asList(workExp));
        verify(user).setEducation(Arrays.asList(education));
        verify(user).setSkills(Arrays.asList(skills));
        verify(userDataAccess).updateUser(user);
        verify(presenter).present(any(UserInputOutputData.class));
    }

    @Test
    public void testGetUserDataWhenUserExists() {
        when(userDataAccess.getCurrentUser()).thenReturn(user);
        when(user.getFullName()).thenReturn("John Doe");
        when(user.getEmail()).thenReturn("john@example.com");
        when(user.getWorkExperience()).thenReturn(Arrays.asList("Company A"));
        when(user.getEducation()).thenReturn(Arrays.asList("University X"));
        when(user.getSkills()).thenReturn(Arrays.asList("Java"));

        interactor.getUserData();

        verify(presenter).refresh(argThat(outputData ->
                outputData.getFullname().equals("John Doe") &&
                        outputData.getEmail().equals("john@example.com") &&
                        outputData.getWorkexperience().equals(Arrays.asList("Company A")) &&
                        outputData.getEducation().equals(Arrays.asList("University X")) &&
                        outputData.getSkills().equals(Arrays.asList("Java"))
        ));
    }

    @Test
    public void testGetUserDataWhenUserDoesNotExist() {
        // Simulate no user being logged in
        when(userDataAccess.getCurrentUser()).thenReturn(null);

        interactor.getUserData();

        // Verify that refresh() was called with empty values
        verify(presenter).refresh(argThat(outputData ->
                outputData.getFullname().isEmpty() &&
                        outputData.getEmail().isEmpty() &&
                        outputData.getWorkexperience().isEmpty() &&
                        outputData.getEducation().isEmpty() &&
                        outputData.getSkills().isEmpty()
        ));
    }

}
