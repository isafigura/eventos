package controller;

import dao.FileUserDao;
import dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import util.HashUtil;
import controller.SceneController;


public class RegisterController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final UserDao userDao = new FileUserDao();

    @FXML
    protected void handleRegister() {

        String name = nameField.getText();
        String email = emailField.getText();
        String pass = passwordField.getText();

        if (name.isBlank() || email.isBlank() || pass.isBlank()) {
            errorLabel.setText("Preencha todos os campos!");
            return;
        }

        if (userDao.findByEmail(email) != null) {
            errorLabel.setText("Email já cadastrado!");
            return;
        }

        User user = new User(name, email, HashUtil.md5(pass));
        userDao.save(user);

        SceneController.changeScene("login.fxml");
    }

    @FXML
    protected void backToLogin() {
        SceneController.changeScene("login.fxml");
    }
}
