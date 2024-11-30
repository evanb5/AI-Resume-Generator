// test/use_case/CVhistory/CVhistoryInteractorTest.java
package use_case.CVhistory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import data_access.UserDataAccessInterface;
import entity.User;

import java.util.*;

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

        when(userDataAccess.getCurrentUser()).thenReturn(user);
        interactor = new CVhistoryInteractor(userDataAccess, presenter);
    }

    @Test
    public void testCVhistoryWithValidIndex() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");
        cvMap.put("CV2", "Content2");

        when(user.getCvs()).thenReturn(cvMap);

        CVhistoryInputData inputData = new CVhistoryInputData(1);

        interactor.CVhistory(inputData);

        verify(user).setCVindex(1);
        verify(presenter).present(any(CVhistoryOutputData.class));
    }

    @Test
    public void testCVhistoryDefaultCaseWhenCVIndexIsNegative() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");

        when(user.getCvs()).thenReturn(cvMap);
        when(user.getCVindex()).thenReturn(-1); // Negative index

        CVhistoryInputData inputData = new CVhistoryInputData(999);

        interactor.CVhistory(inputData);

        verify(user, never()).setCVindex(anyInt());
        List<String> expectedKeys = new ArrayList<>(cvMap.keySet());
        verify(presenter).present(argThat(outputData ->
                outputData.getCVhistory().equals("") &&
                        outputData.getTitles().equals(expectedKeys)
        ));
    }

    @Test
    public void testCVhistoryDefaultCaseWhenCVIndexExceedsSize() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");

        when(user.getCvs()).thenReturn(cvMap);
        when(user.getCVindex()).thenReturn(2); // Index exceeds size

        CVhistoryInputData inputData = new CVhistoryInputData(999);

        interactor.CVhistory(inputData);

        verify(user, never()).setCVindex(anyInt());
        List<String> expectedKeys = new ArrayList<>(cvMap.keySet());
        verify(presenter).present(argThat(outputData ->
                outputData.getCVhistory().equals("") &&
                        outputData.getTitles().equals(expectedKeys)
        ));
    }

    @Test
    public void testCVhistoryWithIndexMinus4() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");
        cvMap.put("CV2", "Content2");

        when(user.getCvs()).thenReturn(cvMap);

        CVhistoryInputData inputData = new CVhistoryInputData(-4);

        interactor.CVhistory(inputData);

        verify(user).setCVindex(-4);
        verify(presenter).present(any(CVhistoryOutputData.class));
    }

    @Test
    public void testCVhistoryWithIndexMinus2WhenCVIndexGreaterThanZero() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");
        cvMap.put("CV2", "Content2");

        when(user.getCvs()).thenReturn(cvMap);
        when(user.getCVindex()).thenReturn(1);

        CVhistoryInputData inputData = new CVhistoryInputData(-2);

        interactor.CVhistory(inputData);

        verify(user).setCVindex(0);
        verify(presenter).present(any(CVhistoryOutputData.class));
    }

    @Test
    public void testCVhistoryWithIndexMinus2WhenCVIndexNotGreaterThanZero() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");

        when(user.getCvs()).thenReturn(cvMap);
        when(user.getCVindex()).thenReturn(0);

        CVhistoryInputData inputData = new CVhistoryInputData(-2);

        interactor.CVhistory(inputData);

        verify(user, never()).setCVindex(anyInt());
        verify(presenter).present(any(CVhistoryOutputData.class));
    }

    @Test
    public void testCVhistoryWithIndexMinus3ValidNextCV() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");
        cvMap.put("CV2", "Content2");
        cvMap.put("CV3", "Content3");

        when(user.getCvs()).thenReturn(cvMap);
        when(user.getCVindex()).thenReturn(1);

        CVhistoryInputData inputData = new CVhistoryInputData(-3);

        interactor.CVhistory(inputData);

        verify(user).setCVindex(2);
        List<String> expectedKeys = new ArrayList<>(cvMap.keySet());
        verify(presenter).present(argThat(outputData ->
                outputData.getCVhistory().equals("Content3") &&
                        outputData.getTitles().equals(expectedKeys)
        ));
    }

    @Test
    public void testCVhistoryWithIndexMinus3InvalidNextCVWhenAtLastIndex() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");
        cvMap.put("CV2", "Content2");

        when(user.getCvs()).thenReturn(cvMap);
        when(user.getCVindex()).thenReturn(1);

        CVhistoryInputData inputData = new CVhistoryInputData(-3);

        interactor.CVhistory(inputData);

        verify(user, never()).setCVindex(anyInt());
        List<String> expectedKeys = new ArrayList<>(cvMap.keySet());
        verify(presenter).present(argThat(outputData ->
                outputData.getCVhistory().equals("Content2") &&
                        outputData.getTitles().equals(expectedKeys)
        ));
    }

    @Test
    public void testCVhistoryWithIndexMinus3InvalidNextCVWhenNegativeIndex() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");

        when(user.getCvs()).thenReturn(cvMap);
        when(user.getCVindex()).thenReturn(-1);

        CVhistoryInputData inputData = new CVhistoryInputData(-3);

        interactor.CVhistory(inputData);

        verify(user, never()).setCVindex(anyInt());
        List<String> expectedKeys = new ArrayList<>(cvMap.keySet());
        verify(presenter).present(argThat(outputData ->
                outputData.getCVhistory().equals("") &&
                        outputData.getTitles().equals(expectedKeys)
        ));
    }

    @Test
    public void testCVhistoryWithIndexOutOfBounds() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");

        when(user.getCvs()).thenReturn(cvMap);
        when(user.getCVindex()).thenReturn(-4);

        CVhistoryInputData inputData = new CVhistoryInputData(5);

        interactor.CVhistory(inputData);

        verify(user, never()).setCVindex(anyInt());
        List<String> expectedKeys = new ArrayList<>(cvMap.keySet());
        verify(presenter).present(argThat(outputData ->
                outputData.getCVhistory().equals("") &&
                        outputData.getTitles().equals(expectedKeys)
        ));
    }

    @Test
    public void testCVhistoryDefaultCaseWithCVIndexNotMinus4() {
        Map<String, String> cvMap = new LinkedHashMap<>();
        cvMap.put("CV1", "Content1");

        when(user.getCvs()).thenReturn(cvMap);
        when(user.getCVindex()).thenReturn(0);

        CVhistoryInputData inputData = new CVhistoryInputData(999);

        interactor.CVhistory(inputData);

        verify(user, never()).setCVindex(anyInt());
        List<String> expectedKeys = new ArrayList<>(cvMap.keySet());
        verify(presenter).present(argThat(outputData ->
                outputData.getCVhistory().equals("Content1") &&
                        outputData.getTitles().equals(expectedKeys)
        ));
    }

    @Test
    public void testCVhistoryDefaultCaseWithCVIndexMinus4() {
        Map<String, String> cvMap = new LinkedHashMap<>();

        when(user.getCvs()).thenReturn(cvMap);
        when(user.getCVindex()).thenReturn(-4);

        CVhistoryInputData inputData = new CVhistoryInputData(999);

        interactor.CVhistory(inputData);

        verify(user, never()).setCVindex(anyInt());
        List<String> expectedKeys = new ArrayList<>(cvMap.keySet());
        verify(presenter).present(argThat(outputData ->
                outputData.getCVhistory().equals("") &&
                        outputData.getTitles().equals(expectedKeys)
        ));
    }

    @Test
    public void testCVhistoryWithNoUser() {
        when(userDataAccess.getCurrentUser()).thenReturn(null);

        CVhistoryInputData inputData = new CVhistoryInputData(0);

        interactor.CVhistory(inputData);

        verify(presenter).present(any(CVhistoryOutputData.class));
    }
}
