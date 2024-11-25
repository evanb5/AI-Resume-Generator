// app/AppBuilder.java
package app;

import data_access.*;
import entity.*;
import interface_adapter.*;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.login.*;
import interface_adapter.signup.*;
import interface_adapter.user_input.*;
import interface_adapter.build_resume.*;
import interface_adapter.build_cv.*;
import interface_adapter.give_suggestions.*;
import use_case.HistoryNumber.HistoryInteractor;
import use_case.login.*;
import use_case.signup.*;
import use_case.user_input.*;
import use_case.build_resume.*;
import use_case.build_cv.*;
import use_case.give_suggestions.*;
import view.*;

public class AppBuilder {
    public void build() {
        UserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();

        UserFactory userFactory = new CommonUserFactory();

        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        UserInputViewModel userInputViewModel = new UserInputViewModel();
        BuildResumeViewModel buildResumeViewModel = new BuildResumeViewModel();
        BuildCVViewModel buildCVViewModel = new BuildCVViewModel();
        GiveSuggestionsViewModel giveSuggestionsViewModel = new GiveSuggestionsViewModel();
        HistoryViewModel historyViewModel = new HistoryViewModel();

        LoginPresenter loginPresenter = new LoginPresenter(loginViewModel);
        SignupPresenter signupPresenter = new SignupPresenter(loginViewModel, signupViewModel);
        UserInputPresenter userInputPresenter = new UserInputPresenter();
        BuildResumePresenter buildResumePresenter = new BuildResumePresenter();
        BuildCVPresenter buildCVPresenter = new BuildCVPresenter();
        GiveSuggestionsPresenter giveSuggestionsPresenter = new GiveSuggestionsPresenter();
        HistoryPresenter historyPresenter = new HistoryPresenter();

        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccess, loginPresenter);
        SignupInputBoundary signupInteractor = new SignupInteractor(userDataAccess, userFactory, signupPresenter);
        UserInputInputBoundary userInputInteractor = new UserInputInteractor(userDataAccess, userInputPresenter);
        BuildResumeInputBoundary buildResumeInteractor = new BuildResumeInteractor(userDataAccess, buildResumePresenter);
        BuildCVInputBoundary buildCVInteractor = new BuildCVInteractor(userDataAccess, buildCVPresenter);
        GiveSuggestionsInputBoundary giveSuggestionsInteractor = new GiveSuggestionsInteractor(userDataAccess, giveSuggestionsPresenter);
        HistoryInteractor historyInteractor = new HistoryInteractor(userDataAccess, historyPresenter);

        LoginController loginController = new LoginController(loginInteractor);
        SignupController signupController = new SignupController(signupInteractor);
        UserInputController userInputController = new UserInputController(userInputInteractor);
        BuildResumeController buildResumeController = new BuildResumeController(buildResumeInteractor);
        BuildCVController buildCVController = new BuildCVController(buildCVInteractor);
        GiveSuggestionsController giveSuggestionsController = new GiveSuggestionsController(giveSuggestionsInteractor);
        HistoryController historyController = new HistoryController(historyInteractor);

        ViewManager viewManager = new ViewManager(
                loginController,
                signupController,
                userInputController,
                buildResumeController,
                buildCVController,
                giveSuggestionsController,
                historyController,
                loginViewModel,
                signupViewModel,
                userInputPresenter,
                buildResumePresenter,
                buildCVPresenter,
                giveSuggestionsPresenter,
                historyPresenter
        );

        viewManager.showLoginView();
    }
}
