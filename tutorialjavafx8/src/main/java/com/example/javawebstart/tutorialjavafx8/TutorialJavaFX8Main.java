package com.example.javawebstart.tutorialjavafx8;

import com.example.javawebstart.tutorialjavafx8.modelo.Persona;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TutorialJavaFX8Main extends Application {
	private static final Logger log = LogManager.getLogger();
	private Stage ventanaPrincipal;
	private BorderPane layoutRaiz;
	private ObservableList<Persona> personas = FXCollections.observableArrayList();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.ventanaPrincipal.setTitle("Tutorial Java FX 8");

		// Añadimos datos de prueba
		personas.add(new Persona("Hans", "Muster"));
		personas.add(new Persona("Ruth", "Mueller"));
		personas.add(new Persona("Heinz", "Kurz"));
		personas.add(new Persona("Cornelia", "Meier"));
		personas.add(new Persona("Werner", "Meyer"));
		personas.add(new Persona("Lydia", "Kunz"));
		personas.add(new Persona("Anna", "Best"));
		personas.add(new Persona("Stefan", "Meier"));
		personas.add(new Persona("Martin", "Mueller"));

		iniciarLayoutRaiz();

		mostrarPersonaVistaGeneral();

	}

	private void iniciarLayoutRaiz() {
		try {
			//Cargamos el layout raiz del fichero fxml
			FXMLLoader cargadorFxml = new FXMLLoader();
			cargadorFxml.setLocation(getClass().getResource("/com/example/javawebstart/tutorialjavafx8/vista/VistaInicial.fxml"));
			layoutRaiz = cargadorFxml.load();

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
			AnchorPane vistaGeneralPersona = cargadorFxml.load();

			//Poner la vista de persona en el panel central
			layoutRaiz.setCenter(vistaGeneralPersona);

			// Le damos al controlador acceso a la aplicacion principal
			PersonaGeneralControlador controller = cargadorFxml.getController();
			controller.setMain(this);
		} catch (Exception e) {
			log.error("No se ha podido cargar el layout inicial", e);
		}
	}

	public ObservableList<Persona> getPersonas() {
		return personas;
	}

}