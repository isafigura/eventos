package app;

import controller.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SceneController.setStage(stage);
        SceneController.switchTo("login.fxml", "City Events - Login");
    }

    public static void main(String[] args) {
        launch();
    }
}
