package controller;

import dao.FileEventDao;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import model.Event;
import java.util.*;

public class ChartController {

    @FXML
    private BarChart<String, Number> barChart;

    private FileEventDao eventDao = new FileEventDao();

    @FXML
    public void initialize() {

        Map<String, Integer> map = new HashMap<>();

        for (Event e : eventDao.findAll()) {
            map.merge(e.getCategory(), 1, Integer::sum);
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        map.forEach((cat, qtd) -> {
            series.getData().add(new XYChart.Data<>(cat, qtd));
        });

        barChart.getData().add(series);
    }

    @FXML
    public void back() {
        SceneController.changeScene("event_list.fxml");
    }
}
