package controller;

import dao.EventDao;
import dao.FileEventDao;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Event;

public class EventListController {

    @FXML
    private ListView<String> eventList;

    private final EventDao eventDao = new FileEventDao();

    @FXML
    public void initialize() {
        refreshList(); // carrega os eventos do arquivo
    }

    public void refreshList() {
        eventList.getItems().clear();
        for (Event e : eventDao.findAll()) {
            String itemText = String.format(
                    "Título: %s%nData: %s%nCategoria: %s%nDescrição: %s%n-----------------------------",
                    e.getName(),
                    e.getDate(),
                    e.getCategory(),
                    e.getDescription()
            );
            eventList.getItems().add(itemText);
        }
    }


    @FXML
    protected void addEvent() {
        // vai para a tela de criação de evento
        SceneController.switchTo("create_event.fxml", "Novo Evento");
    }

    @FXML
    protected void openChart() {
        SceneController.changeScene("chart.fxml");
    }

    @FXML
    protected void logout() {
        SceneController.setLoggedUser(null);
        SceneController.changeScene("login.fxml");
    }
}
