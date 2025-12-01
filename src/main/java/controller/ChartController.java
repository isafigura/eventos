package controller;

import dao.FileEventDao;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import model.Event;

import java.util.HashMap;
import java.util.Map;

public class ChartController {

    @FXML
    private BarChart<String, Number> barChart;

    private final FileEventDao eventDao = new FileEventDao();

    private final Map<String, String> categoryColors = new HashMap<>() {{
        put("Cultura", "#4caf50");     // verde
        put("Esporte", "#2196f3");     // azul
        put("Educação", "#ff9800");    // laranja
        put("Saúde", "#f44336");       // vermelho
        put("Tecnologia", "#9c27b0");  // roxo
        put("Outro", "#607d8b");       // cinza
        put("Sem Categoria", "#9e9e9e");// cinza claro
    }};

    @FXML
    public void initialize() {
        loadChart();
    }

    private void loadChart() {
        barChart.getData().clear();

        Map<String, Integer> categoryCount = new HashMap<>();

        for (Event e : eventDao.findAll()) {
            String cat = e.getCategory();
            if (cat == null || cat.isEmpty()) {
                cat = "Sem Categoria";
            }
            categoryCount.merge(cat, 1, Integer::sum);
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Eventos por Categoria");

        int index = 0;
        for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            XYChart.Data<String, Number> data = new XYChart.Data<>(entry.getKey(), entry.getValue());
            series.getData().add(data);

            String color = categoryColors.getOrDefault(entry.getKey(), "#000000");
            data.nodeProperty().addListener((obs, oldNode, newNode) -> {
                if (newNode != null) {
                    newNode.setStyle("-fx-bar-fill: " + color + ";");
                }
            });

            index++;
        }

        barChart.getData().add(series);
    }

    @FXML
    public void back() {
        SceneController.changeScene("event_list.fxml");
    }
}
