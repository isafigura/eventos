package controller;

import dao.EventDao;
import dao.FileEventDao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Event;

public class CreateEventController {

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label messageLabel;

    private final EventDao eventDao = new FileEventDao();

    @FXML
    public void initialize() {
        categoryComboBox.setItems(FXCollections.observableArrayList(
                "Cultura", "Esporte", "Educação", "Saúde", "Tecnologia", "Outro"
        ));
    }

    @FXML
    private void saveEvent() {
        String title = titleField.getText().trim();
        String category = categoryComboBox.getValue(); // pega do ComboBox
        String description = descriptionArea.getText().trim();
        String date = datePicker.getValue() != null ? datePicker.getValue().toString() : "";

        if (title.isEmpty() || date.isEmpty() || category == null || category.isEmpty()) {
            messageLabel.setText("Título, data e categoria são obrigatórios!");
            return;
        }

        Event event = new Event(title, description, date, category);
        eventDao.save(event);

        SceneController.switchTo("event_list.fxml", "Eventos");
    }

    @FXML
    private void back() {
        SceneController.switchTo("event_list.fxml", "Eventos");
    }
}
