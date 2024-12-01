// test/use_case/CVhistory/CVhistoryInteractorTest.java
package use_case.CVhistory;

import data_access.UserDataAccessInterface;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.*;

public class CVhistoryInteractorTest {

    private CVhistoryInteractor interactor;
    private UserDataAccessInterface userDataAccess;
    private CVhistoryOutputBoundary presenter;
    private User user;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(UserDataAccessInterface.class);
        presenter = mock(CVhistoryOutputBoundary.class);
        user = mock(User.class);

        interactor = new CVhistoryInteractor(userDataAccess, presenter);
    }

    @Test
    public void testCVhistoryWithValidIndex() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");
        cvMap.put("CV2", "Content2");

        when(userDataAccess.getCurrentUser()).thenReturn(user);
        when(user.getUsername()).thenReturn("john_doe");
        when(userDataAccess.getCvs("john_doe")).thenReturn((HashMap<String, String>) cvMap);
        when(userDataAccess.getCvContent("john_doe", "CV2")).thenReturn("Content2");

        CVhistoryInputData inputData = new CVhistoryInputData(1);

        interactor.CVhistory(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getCVhistory().equals("Content2") &&
                        outputData.getTitles().equals(new ArrayList<>(cvMap.keySet()))
        ));
    }

    @Test
    public void testCVhistoryWithInvalidIndex() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");

        when(userDataAccess.getCurrentUser()).thenReturn(user);
        when(user.getUsername()).thenReturn("john_doe");
        when(userDataAccess.getCvs("john_doe")).thenReturn((HashMap<String, String>) cvMap);

        CVhistoryInputData inputData = new CVhistoryInputData(5);

        interactor.CVhistory(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getCVhistory().isEmpty() &&
                        outputData.getTitles().isEmpty()
        ));
    }

    @Test
    public void testCVhistoryWithAllTitles() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");
        cvMap.put("CV2", "Content2");

        when(userDataAccess.getCurrentUser()).thenReturn(user);
        when(user.getUsername()).thenReturn("john_doe");
        when(userDataAccess.getCvs("john_doe")).thenReturn((HashMap<String, String>) cvMap);

        CVhistoryInputData inputData = new CVhistoryInputData(-1);

        interactor.CVhistory(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getCVhistory().isEmpty() &&
                        outputData.getTitles().equals(new ArrayList<>(cvMap.keySet()))
        ));
    }

    @Test
    public void testCVhistoryWithNoUser() {
        when(userDataAccess.getCurrentUser()).thenReturn(null);

        CVhistoryInputData inputData = new CVhistoryInputData(0);

        interactor.CVhistory(inputData);

        verify(presenter).present(argThat(outputData ->
                outputData.getCVhistory().isEmpty() &&
                        outputData.getTitles().isEmpty()
        ));
    }
}
