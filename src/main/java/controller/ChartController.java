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

    @FXML
    public void initialize() {
        loadChart();
    }

    private void loadChart() {
        barChart.getData().clear(); // limpa dados antigos

        // Conta eventos por categoria
        Map<String, Integer> categoryCount = new HashMap<>();

        for (Event e : eventDao.findAll()) {
            String cat = e.getCategory();
            if (cat == null || cat.isEmpty()) {
                cat = "Sem Categoria"; // categorias vazias
            }
            categoryCount.merge(cat, 1, Integer::sum);
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Eventos por Categoria");

        // Adiciona cada categoria ao gráfico
        categoryCount.forEach((cat, qtd) -> {
            series.getData().add(new XYChart.Data<>(cat, qtd));
        });

        barChart.getData().add(series);
    }

    @FXML
    public void back() {
        SceneController.changeScene("event_list.fxml");
    }
}
