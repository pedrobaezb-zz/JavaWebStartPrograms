package com.example.javawebstart.tutorialjavafx8;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TutorialJavaFX8Main extends Application {
	private static final Logger log = LogManager.getLogger();
	private Stage ventanaPrincipal;
	private BorderPane layoutRaiz;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.ventanaPrincipal.setTitle("Tutorial Java FX 8");

		iniciarLayoutRaiz();

		mostrarPersonaVistaGeneral();

	}

	private void iniciarLayoutRaiz() {
		try {
			//Cargamos el layout raiz del fichero fxml
			FXMLLoader cargadorFxml = new FXMLLoader();
			cargadorFxml.setLocation(getClass().getResource("/com/example/javawebstart/tutorialjavafx8/vista/VistaInicial.fxml"));
			layoutRaiz = (BorderPane) cargadorFxml.load();

			//Enseña ña escenta con el layout raiz
			Scene ventana = new Scene(layoutRaiz);
			ventanaPrincipal.setScene(ventana);
			ventanaPrincipal.show();
		} catch (Exception e) {
			log.error("No se ha podido cargar el layout inicial", e);
		}
	}

	private void mostrarPersonaVistaGeneral() {
		try {
			//Cargar la vista general de persona
			FXMLLoader cargadorFxml = new FXMLLoader();
			cargadorFxml.setLocation(getClass().getResource("/com/example/javawebstart/tutorialjavafx8/vista/PersonaVistaGeneral.fxml"));
			AnchorPane vistaGeneralPersona = (AnchorPane) cargadorFxml.load();

			//Poner la vista de persona en el panel central
			layoutRaiz.setCenter(vistaGeneralPersona);
		} catch (Exception e) {
			log.error("No se ha podido cargar el layout inicial", e);
		}
	}

}