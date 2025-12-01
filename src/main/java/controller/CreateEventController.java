package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class CreateEventController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label messageLabel;

    @FXML
    private void saveEvent(ActionEvent event) {
        // lógica para salvar evento
        messageLabel.setText("Evento salvo!");
    }

    @FXML
    public void back() {
        // Volta para a lista
        SceneController.changeScene("event_list.fxml");

        // Atualiza os eventos da lista
        EventListController controller = SceneController.getController(EventListController.class);
        if (controller != null) {
            controller.refreshList();
        }
    }


}
