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
        refreshList();
    }

    public void refreshList() {
        eventList.getItems().clear();
        for (Event e : eventDao.findAll()) {
            eventList.getItems().add(e.getName() + " — " + e.getDate());
        }
    }


    @FXML
    protected void addEvent() {
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
