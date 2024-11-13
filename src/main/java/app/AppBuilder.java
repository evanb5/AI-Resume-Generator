// app/AppBuilder.java
package app;

import data_access.*;
import entity.*;
import interface_adapter.*;
import interface_adapter.login.*;
import interface_adapter.signup.*;
import interface_adapter.user_input.*;
import interface_adapter.build_resume.*;
import interface_adapter.build_cv.*;
import interface_adapter.give_suggestions.*;
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

        LoginPresenter loginPresenter = new LoginPresenter();
        SignupPresenter signupPresenter = new SignupPresenter();
        UserInputPresenter userInputPresenter = new UserInputPresenter();
        BuildResumePresenter buildResumePresenter = new BuildResumePresenter();
        BuildCVPresenter buildCVPresenter = new BuildCVPresenter();
        GiveSuggestionsPresenter giveSuggestionsPresenter = new GiveSuggestionsPresenter();

        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccess, loginPresenter);
        SignupInputBoundary signupInteractor = new SignupInteractor(userDataAccess, userFactory, signupPresenter);
        UserInputInputBoundary userInputInteractor = new UserInputInteractor(userDataAccess, userInputPresenter);
        BuildResumeInputBoundary buildResumeInteractor = new BuildResumeInteractor(userDataAccess, buildResumePresenter);
        BuildCVInputBoundary buildCVInteractor = new BuildCVInteractor(userDataAccess, buildCVPresenter);
        GiveSuggestionsInputBoundary giveSuggestionsInteractor = new GiveSuggestionsInteractor(userDataAccess, giveSuggestionsPresenter);

        // 初始化 Controller
        LoginController loginController = new LoginController(loginInteractor);
        SignupController signupController = new SignupController(signupInteractor);
        UserInputController userInputController = new UserInputController(userInputInteractor);
        BuildResumeController buildResumeController = new BuildResumeController(buildResumeInteractor);
        BuildCVController buildCVController = new BuildCVController(buildCVInteractor);
        GiveSuggestionsController giveSuggestionsController = new GiveSuggestionsController(giveSuggestionsInteractor);

        ViewManager viewManager = new ViewManager(
                loginController,
                signupController,
                userInputController,
                buildResumeController,
                buildCVController,
                giveSuggestionsController,
                loginPresenter,
                signupPresenter,
                userInputPresenter,
                buildResumePresenter,
                buildCVPresenter,
                giveSuggestionsPresenter
        );

        viewManager.showLoginView();
    }
}
