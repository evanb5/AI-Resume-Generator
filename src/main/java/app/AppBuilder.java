package app;

import data_access.*;
import entity.*;
import interface_adapter.*;
import interface_adapter.CVhistory.CVhistoryController;
import interface_adapter.CVhistory.CVhistoryPresenter;
import interface_adapter.CVhistory.CVhistoryViewModel;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.login.*;
import interface_adapter.signup.*;
import interface_adapter.user_input.*;
import interface_adapter.build_resume.*;
import interface_adapter.build_cv.*;
import interface_adapter.give_suggestions.*;
import interface_adapter.resume_history.ResumeHistoryController;
import interface_adapter.resume_history.ResumeHistoryPresenter;
import interface_adapter.resume_history.ResumeHistoryViewModel;
import use_case.CVhistory.CVhistoryInteractor;
import use_case.HistoryNumber.HistoryInteractor;
import use_case.login.*;
import use_case.signup.*;
import use_case.user_input.*;
import use_case.build_resume.*;
import use_case.build_cv.*;
import use_case.give_suggestions.*;
import use_case.resume_history.ResumeHistoryInteractor;
import view.*;

public class AppBuilder {
    public void build() {
        UserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();
        ResumeFactory resumeFactory = new CommonResumeFactory();

        // View Models
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        UserInputViewModel userInputViewModel = new UserInputViewModel();
        BuildResumeViewModel buildResumeViewModel = new BuildResumeViewModel();
        BuildCVViewModel buildCVViewModel = new BuildCVViewModel();
        GiveSuggestionsViewModel giveSuggestionsViewModel = new GiveSuggestionsViewModel();
        HistoryViewModel historyViewModel = new HistoryViewModel();
        CVhistoryViewModel cvhistoryViewModel = new CVhistoryViewModel();
        ResumeHistoryViewModel resumeHistoryViewModel = new ResumeHistoryViewModel();

        // Presenters
        LoginPresenter loginPresenter = new LoginPresenter(loginViewModel);
        SignupPresenter signupPresenter = new SignupPresenter(loginViewModel, signupViewModel);
        UserInputPresenter userInputPresenter = new UserInputPresenter(userInputViewModel);
        BuildResumePresenter buildResumePresenter = new BuildResumePresenter(buildResumeViewModel);
        BuildCVPresenter buildCVPresenter = new BuildCVPresenter(buildCVViewModel);
        GiveSuggestionsPresenter giveSuggestionsPresenter = new GiveSuggestionsPresenter(giveSuggestionsViewModel);
        HistoryPresenter historyPresenter = new HistoryPresenter(historyViewModel);
        CVhistoryPresenter cvhistoryPresenter = new CVhistoryPresenter(cvhistoryViewModel);
        ResumeHistoryPresenter resumeHistoryPresenter = new ResumeHistoryPresenter(resumeHistoryViewModel);

        // Interactors
        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccess, loginPresenter);
        SignupInputBoundary signupInteractor = new SignupInteractor(userDataAccess, userFactory, signupPresenter);
        UserInputInputBoundary userInputInteractor = new UserInputInteractor(userDataAccess, userInputPresenter);
        BuildResumeInputBoundary buildResumeInteractor = new BuildResumeInteractor(userDataAccess, buildResumePresenter,
                resumeFactory);
        BuildCVInputBoundary buildCVInteractor = new BuildCVInteractor(userDataAccess, buildCVPresenter);
        GiveSuggestionsInputBoundary giveSuggestionsInteractor = new GiveSuggestionsInteractor(userDataAccess, giveSuggestionsPresenter);
        HistoryInteractor historyInteractor = new HistoryInteractor(userDataAccess, historyPresenter);
        CVhistoryInteractor cvhistoryInteractor = new CVhistoryInteractor(userDataAccess, cvhistoryPresenter);
        ResumeHistoryInteractor resumeHistoryInteractor = new ResumeHistoryInteractor(userDataAccess, resumeHistoryPresenter);

        // Controllers
        LoginController loginController = new LoginController(loginInteractor);
        SignupController signupController = new SignupController(signupInteractor);
        UserInputController userInputController = new UserInputController(userInputInteractor);
        BuildResumeController buildResumeController = new BuildResumeController(buildResumeInteractor);
        BuildCVController buildCVController = new BuildCVController(buildCVInteractor);
        GiveSuggestionsController giveSuggestionsController = new GiveSuggestionsController(giveSuggestionsInteractor);
        HistoryController historyController = new HistoryController(historyInteractor);
        CVhistoryController CVhistorycontroller = new CVhistoryController(cvhistoryInteractor);
        ResumeHistoryController resumeHistoryController = new ResumeHistoryController(resumeHistoryInteractor);

        // View Manager
        ViewManager viewManager = new ViewManager(
                loginController,
                signupController,
                userInputController,
                buildResumeController,
                buildCVController,
                giveSuggestionsController,
                historyController,
                CVhistorycontroller,
                resumeHistoryController,
                loginViewModel,
                signupViewModel,
                userInputViewModel,
                buildResumeViewModel,
                buildCVViewModel,
                giveSuggestionsViewModel,
                historyViewModel,
                cvhistoryViewModel,
                resumeHistoryViewModel
        );


        viewManager.showLoginView();
    }
}
