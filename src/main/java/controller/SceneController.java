package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneController {

    private static Stage stage;
    private static User loggedUser;

    // Armazena os controllers carregados
    private static final Map<String, Object> controllers = new HashMap<>();

    public static void setStage(Stage s) {
        stage = s;
    }

    public static void setLoggedUser(User user) {
        loggedUser = user;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    /**
     * Troca a cena e mantém referência do controller
     */
    public static void switchTo(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/com/example/cityeventsjavafx/" + fxml));
            Parent root = loader.load();

            // Guarda o controller carregado
            controllers.put(fxml, loader.getController());

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar FXML: " + fxml);
        }
    }

    /**
     * Troca a cena sem alterar o título do Stage
     */
    public static void changeScene(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/com/example/cityeventsjavafx/" + fxml));
            Parent root = loader.load();
            controllers.put(fxml, loader.getController());
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar FXML: " + fxml);
        }
    }

    /**
     * Retorna o controller de um FXML carregado
     */
    @SuppressWarnings("unchecked")
    public static <T> T getController(Class<T> clazz) {
        for (Object controller : controllers.values()) {
            if (clazz.isInstance(controller)) {
                return (T) controller;
            }
        }
        return null;
    }
}
