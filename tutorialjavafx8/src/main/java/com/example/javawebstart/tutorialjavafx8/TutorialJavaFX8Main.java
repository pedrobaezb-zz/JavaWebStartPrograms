package com.example.javawebstart.tutorialjavafx8;

import com.example.javawebstart.tutorialjavafx8.modelo.Persona;
import com.example.javawebstart.tutorialjavafx8.modelo.PersonaListWrapper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.prefs.Preferences;

public class TutorialJavaFX8Main extends Application {
	private static final Logger log = LogManager.getLogger();
	private Stage ventanaPrincipal;
	private BorderPane layoutRaiz;
	private ObservableList<Persona> personas = FXCollections.observableArrayList();
	BooleanProperty ready = new SimpleBooleanProperty(false);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage ventanaPrincipal) {
		longStart();
		this.ventanaPrincipal = ventanaPrincipal;
		this.ventanaPrincipal.setTitle("Tutorial Java FX 8 PRUEBA 1");
		this.ventanaPrincipal.getIcons().add(new Image(getClass().getResourceAsStream("/imagenes/Death Note.png")));

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

			ControladorInicial controladorInicial = cargadorFxml.getController();
			controladorInicial.setTutorialJavaFX8Main(this);

			// After the app is ready, show the stage
			ready.addListener(new ChangeListener<Boolean>(){
				public void changed(
						ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
					if (Boolean.TRUE.equals(t1)) {
						Platform.runLater(new Runnable() {
							public void run() {
								ventanaPrincipal.show();
							}
						});
					}
				}
			});;

			File ultimaLibreta = getFicheroLibreta();
			if(ultimaLibreta!=null)
				cargarLibretaDeFichero(ultimaLibreta);

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

	public boolean mostrarEditarPersonaDialogo(Persona persona) {
		boolean correcto=false;
		try {
			//Cargamos el dialogo de editar persona
			FXMLLoader cargadorFxml = new FXMLLoader();
			cargadorFxml.setLocation(getClass().getResource("/com/example/javawebstart/tutorialjavafx8/vista/EditarPersonaDialogo.fxml"));
			AnchorPane editarPersonaDialogo = cargadorFxml.load();


			//Creamos la escena para el dialogo
			Stage dialogo = new Stage();
			dialogo.setTitle("Nueva persona");
			dialogo.initModality(Modality.WINDOW_MODAL);
			dialogo.initOwner(ventanaPrincipal);
			dialogo.setScene(new Scene(editarPersonaDialogo));

			//Cargamos los datos necesarios en el dialogo
			EditarPersonaControlador controlador = cargadorFxml.getController();
			controlador.setVentanaDialogo(dialogo);
			controlador.setPersona(persona);

			dialogo.showAndWait();

			correcto=controlador.isOkPulsado();
		} catch (Exception e) {
			log.error("Error general", e);
		}
		return correcto;
	}

	public ObservableList<Persona> getPersonas() {
		return personas;
	}

	public Stage getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public File getFicheroLibreta() {
		Preferences preferences = Preferences.userNodeForPackage(getClass());
		String fichero = preferences.get("ficheroLibreta", null);
		if(fichero!=null)
			return new File(fichero);
		else
			return null;
	}

	public void setFicheroLibreta(File fichero) {
		Preferences preferences = Preferences.userNodeForPackage(getClass());

		if(fichero!=null) {
			preferences.put("ficheroLibreta", fichero.getPath());
			ventanaPrincipal.setTitle("Tutorial Java FX 8 - " + fichero.getName());
		} else {
			preferences.remove("ficheroLibreta");
			ventanaPrincipal.setTitle("Tutorial Java FX 8");
		}
	}

	public void cargarLibretaDeFichero(File libreta) {
		try {
			JAXBContext contextoXml = JAXBContext.newInstance(PersonaListWrapper.class);
			Unmarshaller lector = contextoXml.createUnmarshaller();

			PersonaListWrapper personasFichero = (PersonaListWrapper)lector.unmarshal(libreta);

			personas.clear();
			personas.addAll(personasFichero.getPersonas());

			setFicheroLibreta(libreta);
		} catch (Exception e) {
			log.error("Error general", e);
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("No se ha podido leer la libreta:\n" + e.getLocalizedMessage() );
			alerta.showAndWait();
		}
	}

	public void guardarLibretaDeFichero(File libreta) {
		try {
			JAXBContext contextoXml = JAXBContext.newInstance(PersonaListWrapper.class);
			Marshaller escritor = contextoXml.createMarshaller();
			escritor.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			PersonaListWrapper personasXml = new PersonaListWrapper();
			personasXml.setPersonas(personas);

			escritor.marshal(personasXml, libreta);

			setFicheroLibreta(libreta);

		} catch (Exception e) {
			log.error("Error general", e);
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("No se ha podido guardar la libreta:\n" + e.getLocalizedMessage() );
			alerta.showAndWait();
		}
	}

	public void mostrarEstadisticasNacimiento () {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(getClass().getResource("/com/example/javawebstart/tutorialjavafx8/vista/EstadisticasNacimiento.fxml"));
			AnchorPane vistaEstadisticas = (AnchorPane) cargador.load();
			Stage dialogoEstadisticas = new Stage();
			dialogoEstadisticas.setTitle("Estadisticas de nacimiento");
			dialogoEstadisticas.initModality(Modality.WINDOW_MODAL);
			dialogoEstadisticas.initOwner(ventanaPrincipal);
			dialogoEstadisticas.setScene(new Scene(vistaEstadisticas));

			EstadisticasNacimientoControlador controlador = cargador.getController();
			controlador.poblarGrafica(personas);

			dialogoEstadisticas.show();
		} catch (Exception e) {
			log.error("Error general", e);
		}
	}

	private void longStart() {
		//simulate long init in background
		Task task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				int max = 10;
				for (int i = 1; i <= max; i++) {
					log.info("Cargando... {}", i);
					Thread.sleep(500);
					// Send progress to preloader
					notifyPreloader(new Preloader.ProgressNotification(((double) i)/max));
				}
				// After init is ready, the app is ready to be shown
				// Do this before hiding the preloader stage to prevent the
				// app from exiting prematurely
				ready.setValue(Boolean.TRUE);

				notifyPreloader(new Preloader.StateChangeNotification(
						Preloader.StateChangeNotification.Type.BEFORE_START));

				return null;
			}
		};
		new Thread(task).start();
	}
}