package use_case.CVhistory;

import data_access.InMemoryUserDataAccessObject;
import data_access.UserDataAccessInterface;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CVhistoryTest {
    @Test
    public void CVhistorysuccessTestforRefresh() {
        CVhistoryInputData inputData= new CVhistoryInputData(-4);
        UserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.createUser();
        user.addCv("CV1","aaaaa");
        user.addCv("CV2","bbbbb");
        user.addCv("CV3","ccccc");
        user.addCv("CV4","ddddd");
        userDataAccess.setCurrentUser(user);
        List<String> keys = new ArrayList<>(user.getCvs().keySet());
        CVhistoryOutputBoundary successprenter = new CVhistoryOutputBoundary() {
            @Override
            public void present(CVhistoryOutputData outputData) {
                assertEquals("", outputData.getCVhistory());
                assertEquals(keys, outputData.getTitles());
            }
        };
        CVhistoryInteractor interactor = new CVhistoryInteractor(userDataAccess, successprenter);
        interactor.CVhistory(inputData);
    }

    @Test
    public void CVhistorysuccessTestforshowcertain() {
        CVhistoryInputData inputData= new CVhistoryInputData(2);
        UserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.createUser();
        user.addCv("CV1","aaaaa");
        user.addCv("CV2","bbbbb");
        user.addCv("CV3","ccccc");
        user.addCv("CV4","ddddd");
        userDataAccess.setCurrentUser(user);
        List<String> keys = new ArrayList<>(user.getCvs().keySet());
        CVhistoryOutputBoundary successprenter = new CVhistoryOutputBoundary() {
            @Override
            public void present(CVhistoryOutputData outputData) {
                assertEquals(user.getCvs().get(keys.get(2)), outputData.getCVhistory());
                assertEquals(keys, outputData.getTitles());
            }
        };
        CVhistoryInteractor interactor = new CVhistoryInteractor(userDataAccess, successprenter);
        interactor.CVhistory(inputData);
    }

    @Test
    public void CVhistorysuccessTestfornext() {
        CVhistoryInputData inputData= new CVhistoryInputData(-3);
        UserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.createUser();
        user.addCv("CV1","aaaaa");
        user.addCv("CV2","bbbbb");
        user.addCv("CV3","ccccc");
        user.addCv("CV4","ddddd");
        user.setCVindex(1);
        userDataAccess.setCurrentUser(user);
        List<String> keys = new ArrayList<>(user.getCvs().keySet());
        CVhistoryOutputBoundary successprenter = new CVhistoryOutputBoundary() {
            @Override
            public void present(CVhistoryOutputData outputData) {
                assertEquals(user.getCvs().get(keys.get(2)), outputData.getCVhistory());
                assertEquals(keys, outputData.getTitles());
            }
        };
        CVhistoryInteractor interactor = new CVhistoryInteractor(userDataAccess, successprenter);
        interactor.CVhistory(inputData);
    }

    @Test
    public void CVhistorysuccessTestforprevious() {
        CVhistoryInputData inputData= new CVhistoryInputData(-2);
        UserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.createUser();
        user.addCv("CV1","aaaaa");
        user.addCv("CV2","bbbbb");
        user.addCv("CV3","ccccc");
        user.addCv("CV4","ddddd");
        user.setCVindex(3);
        userDataAccess.setCurrentUser(user);
        List<String> keys = new ArrayList<>(user.getCvs().keySet());
        CVhistoryOutputBoundary successprenter = new CVhistoryOutputBoundary() {
            @Override
            public void present(CVhistoryOutputData outputData) {
                assertEquals(user.getCvs().get(keys.get(2)), outputData.getCVhistory());
                assertEquals(keys, outputData.getTitles());
            }
        };
        CVhistoryInteractor interactor = new CVhistoryInteractor(userDataAccess, successprenter);
        interactor.CVhistory(inputData);
    }

    @Test
    public void CVhistoryfaiilTestfornextfornonext() {
        CVhistoryInputData inputData= new CVhistoryInputData(-3);
        UserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.createUser();
        user.addCv("CV1","aaaaa");
        user.addCv("CV2","bbbbb");
        user.addCv("CV3","ccccc");
        user.addCv("CV4","ddddd");
        user.setCVindex(3);
        userDataAccess.setCurrentUser(user);
        List<String> keys = new ArrayList<>(user.getCvs().keySet());
        CVhistoryOutputBoundary failprenter = new CVhistoryOutputBoundary() {
            @Override
            public void present(CVhistoryOutputData outputData) {
                assertEquals(user.getCvs().get(keys.get(3)), outputData.getCVhistory());
                assertEquals(keys, outputData.getTitles());
            }
        };
        CVhistoryInteractor interactor = new CVhistoryInteractor(userDataAccess, failprenter);
        interactor.CVhistory(inputData);
    }

    @Test
    public void CVhistoryfaiilTestfornextfornoprevious() {
        CVhistoryInputData inputData= new CVhistoryInputData(-2);
        UserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.createUser();
        user.addCv("CV1","aaaaa");
        user.addCv("CV2","bbbbb");
        user.addCv("CV3","ccccc");
        user.addCv("CV4","ddddd");
        user.setCVindex(0);
        userDataAccess.setCurrentUser(user);
        List<String> keys = new ArrayList<>(user.getCvs().keySet());
        CVhistoryOutputBoundary failprenter = new CVhistoryOutputBoundary() {
            @Override
            public void present(CVhistoryOutputData outputData) {
                assertEquals(user.getCvs().get(keys.get(0)), outputData.getCVhistory());
                assertEquals(keys, outputData.getTitles());
            }
        };
        CVhistoryInteractor interactor = new CVhistoryInteractor(userDataAccess, failprenter);
        interactor.CVhistory(inputData);
    }

    @Test
    public void CVhistoryfaiilTestfornextfornocurrent() {
        CVhistoryInputData inputData= new CVhistoryInputData(-2);
        UserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.createUser();
        user.addCv("CV1","aaaaa");
        user.addCv("CV2","bbbbb");
        user.addCv("CV3","ccccc");
        user.addCv("CV4","ddddd");
        user.setCVindex(-4);
        userDataAccess.setCurrentUser(user);
        List<String> keys = new ArrayList<>(user.getCvs().keySet());
        CVhistoryOutputBoundary failprenter = new CVhistoryOutputBoundary() {
            @Override
            public void present(CVhistoryOutputData outputData) {
                assertEquals("", outputData.getCVhistory());
                assertEquals(keys, outputData.getTitles());
            }
        };
        CVhistoryInteractor interactor = new CVhistoryInteractor(userDataAccess, failprenter);
        interactor.CVhistory(inputData);
    }

    @Test
    public void CVhistoryfaiilTestfornulluser() {
        CVhistoryInputData inputData= new CVhistoryInputData(-4);
        UserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.createUser();
        user.addCv("CV1","aaaaa");
        user.addCv("CV2","bbbbb");
        user.addCv("CV3","ccccc");
        user.addCv("CV4","ddddd");
        user.setCVindex(3);
        userDataAccess.setCurrentUser(null);
        List<String> keys = new ArrayList<>(user.getCvs().keySet());
        CVhistoryOutputBoundary failprenter = new CVhistoryOutputBoundary() {
            @Override
            public void present(CVhistoryOutputData outputData) {
                assertEquals("", outputData.getCVhistory());
                assertEquals(new ArrayList<>(), outputData.getTitles());
            }
        };
        CVhistoryInteractor interactor = new CVhistoryInteractor(userDataAccess, failprenter);
        interactor.CVhistory(inputData);
    }
}