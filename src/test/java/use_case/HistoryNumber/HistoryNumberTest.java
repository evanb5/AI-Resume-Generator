package use_case.HistoryNumber;

import data_access.InMemoryUserDataAccessObject;
import data_access.UserDataAccessInterface;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryNumberTest {
    @Test
    public void successtestHistoryNumber() {
        UserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.createUser();
        user.addsuggestion("suggestion1");
        user.addsuggestion("suggestion2");
        user.addResume("resume1");
        user.addCv("aaaaa", "CV1");
        user.addCv("bbbbb", "CV2");
        user.addCv("ccccc", "CV3");
        user.addCv("ddddd", "CV4");
        userDataAccess.setCurrentUser(user);
        HistoryOutputBoundary successpresenter = new HistoryOutputBoundary() {
            @Override
            public void present(HistoryOutputData outputData) {
                assertEquals(2,outputData.getSuggestion());
                assertEquals(1,outputData.getResume());
                assertEquals(4,outputData.getCv());
            }
        };
        HistoryInteractor interactor = new HistoryInteractor(userDataAccess, successpresenter);
        interactor.historyinput();
    }

    @Test
    public void failtestHistoryNumberNUlluser() {
        UserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.createUser();
        user.addsuggestion("suggestion1");
        user.addsuggestion("suggestion2");
        user.addResume("resume1");
        user.addCv("aaaaa", "CV1");
        user.addCv("bbbbb", "CV2");
        user.addCv("ccccc", "CV3");
        user.addCv("ddddd", "CV4");
        userDataAccess.setCurrentUser(null);
        HistoryOutputBoundary failpresenter = new HistoryOutputBoundary() {
            @Override
            public void present(HistoryOutputData outputData) {
                assertEquals(0,outputData.getSuggestion());
                assertEquals(0,outputData.getResume());
                assertEquals(0,outputData.getCv());
            }
        };
        HistoryInteractor interactor = new HistoryInteractor(userDataAccess, failpresenter);
        interactor.historyinput();
    }


}
