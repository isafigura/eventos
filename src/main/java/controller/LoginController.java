package controller;

import dao.FileUserDao;
import dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import util.HashUtil;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private final UserDao userDao = new FileUserDao();

    @FXML
    public void onLogin() {

        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isBlank() || password.isBlank()) {
            messageLabel.setText("Preencha todos os campos!");
            return;
        }

        User user = userDao.findByEmail(email);

        if (user == null) {
            messageLabel.setText("Usuário não encontrado.");
            return;
        }

        String hashed = HashUtil.md5(password);

        if (!hashed.equals(user.getPassword())) {
            messageLabel.setText("Senha incorreta.");
            return;
        }
        
        SceneController.setLoggedUser(user);
        SceneController.changeScene("event_list.fxml");
    }

    @FXML
    public void goToRegister() {
        SceneController.changeScene("register.fxml");
    }
}
