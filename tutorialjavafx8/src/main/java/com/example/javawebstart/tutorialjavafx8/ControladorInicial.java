package com.example.javawebstart.tutorialjavafx8;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by x163209 on 24/02/2016.
 */
public class ControladorInicial {
    private static final Logger log = LogManager.getLogger();

    private TutorialJavaFX8Main tutorialJavaFX8Main;

    @FXML
    private void initialize() {
    }

    @FXML
    private void nuevo() {
        tutorialJavaFX8Main.getPersonas().clear();
        tutorialJavaFX8Main.setFicheroLibreta(null);
    }

    @FXML
    private void abrir() {
        FileChooser selectorLibreta = new FileChooser();
        FileChooser.ExtensionFilter filtroExtensiones = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        selectorLibreta.getExtensionFilters().add(filtroExtensiones);

        // Show save file dialog
        File libreta = selectorLibreta.showOpenDialog(tutorialJavaFX8Main.getVentanaPrincipal());

        if (libreta != null) {
            tutorialJavaFX8Main.cargarLibretaDeFichero(libreta);
        }
    }

    @FXML
    private void guardar() {
        File libreta = tutorialJavaFX8Main.getFicheroLibreta();
        if (libreta != null) {
            tutorialJavaFX8Main.guardarLibretaDeFichero(libreta);
        } else {
            guardarComo();
        }
    }

    @FXML
    private void guardarComo() {
        FileChooser selectorLibreta = new FileChooser();
        FileChooser.ExtensionFilter filtroExtensiones = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        selectorLibreta.getExtensionFilters().add(filtroExtensiones);

        File libreta = selectorLibreta.showSaveDialog(tutorialJavaFX8Main.getVentanaPrincipal());

        if (libreta != null) {
            // Make sure it has the correct extension
            if (!libreta.getPath().endsWith(".xml")) {
                libreta = new File(libreta.getPath() + ".xml");
            }
            tutorialJavaFX8Main.guardarLibretaDeFichero(libreta);
        }
    }

    @FXML
    private void salir() {
        System.exit(0);
    }

    @FXML
    private void acercaDe() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aplicacion libreta");
        alerta.setHeaderText("Acerca de");
        alerta.setContentText("Author: Marco Jakob\nWebsite: http://code.makery.ch");
        alerta.showAndWait();
    }

    @FXML
    private void mostrarEstadisticasPersonas() {
        tutorialJavaFX8Main.mostrarEstadisticasNacimiento();
    }

    public TutorialJavaFX8Main getTutorialJavaFX8Main() {
        return tutorialJavaFX8Main;
    }

    public void setTutorialJavaFX8Main(TutorialJavaFX8Main tutorialJavaFX8Main) {
        this.tutorialJavaFX8Main = tutorialJavaFX8Main;
    }
}
