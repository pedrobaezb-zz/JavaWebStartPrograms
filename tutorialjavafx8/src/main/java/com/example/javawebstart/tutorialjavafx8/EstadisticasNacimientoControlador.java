package com.example.javawebstart.tutorialjavafx8;

import com.example.javawebstart.tutorialjavafx8.modelo.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;

/**
 * Created by x163209 on 24/02/2016.
 */
public class EstadisticasNacimientoControlador {
    private static final Logger log = LogManager.getLogger();

    @FXML
    private BarChart<String, Integer> grafico;

    @FXML
    private CategoryAxis ejeX;

    private ObservableList<String> nombreMeses = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        String[] nombreMeses = DateFormatSymbols.getInstance().getMonths();
        this.nombreMeses.addAll(Arrays.asList(nombreMeses));
        ejeX.setCategories(this.nombreMeses);
    }

    public void poblarGrafica(List<Persona> personas) {
        //Calculamos los datos del grafico
        int[] contadorMeses = new int[12];
        for (Persona p : personas) {
            int mes = p.getNacimiento().getMonthValue() - 1;
            contadorMeses[mes]++;
        }

        XYChart.Series<String, Integer> datos = new XYChart.Series<>();

        //Poblamos la grafica
        for (int i = 0; i < contadorMeses.length; i++)
            datos.getData().add(new XYChart.Data<>(nombreMeses.get(i), contadorMeses[i]));

        grafico.getData().add(datos);
    }

}
