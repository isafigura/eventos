package controller;

import dao.EventDao;
import dao.FileEventDao;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Event;

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
    private javafx.scene.control.Label messageLabel;

    private final EventDao eventDao = new FileEventDao();

    @FXML
    private void saveEvent() {
        String title = titleField.getText();
        String category = categoryField.getText();
        String description = descriptionArea.getText();
        String date = datePicker.getValue() != null ? datePicker.getValue().toString() : "";

        if (title.isEmpty() || date.isEmpty()) {
            messageLabel.setText("Título e data são obrigatórios!");
            return;
        }

        Event event = new Event(title, description, date, category);
        eventDao.save(event);

        // volta para a lista de eventos
        SceneController.switchTo("event_list.fxml", "Eventos");
        // Ao voltar, initialize() do EventListController roda e atualiza a lista
    }

    @FXML
    private void back() {
        SceneController.switchTo("event_list.fxml", "Eventos");
    }
}
