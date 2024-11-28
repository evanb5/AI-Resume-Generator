// view/LoginView.java
package view;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import interface_adapter.login.*;
import session.UserSession;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    private ViewManager viewManager;
    private LoginController controller;
    private LoginViewModel loginViewModel;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private JLabel messageLabel;

    public LoginView(ViewManager viewManager, LoginViewModel viewModel) {
        this.viewManager = viewManager;
        this.loginViewModel = viewModel;
        loginViewModel.addPropertyChangeListener(this);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");
        messageLabel = new JLabel();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(signupButton);
        add(messageLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loginButton) {
                    updateCurrentState();
                    controller.login(usernameField.getText(), new String(passwordField.getPassword()));
                    messageLabel.setText(viewModel.getMessage());
                    if (viewModel.isSuccess()) {
                        UserSession.getInstance().setCurrentUser(viewModel.getUser());
                        viewManager.showUserInputView();
                    }
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showSignupView();
            }
        });
    }

    private void updateCurrentState() {
        final LoginState currentState = loginViewModel.getState();
        currentState.setPassword(Arrays.toString(passwordField.getPassword()));
        currentState.setusername(usernameField.getText());
        loginViewModel.setState(currentState);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameField.setText(state.getUserName());
        passwordField.setText(state.getPassword());
    }

    public void setLoginController(LoginController controller) {
        this.controller = controller;
    }
}
