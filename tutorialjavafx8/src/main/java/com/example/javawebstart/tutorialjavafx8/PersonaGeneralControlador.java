package com.example.javawebstart.tutorialjavafx8;


import com.example.javawebstart.tutorialjavafx8.modelo.Persona;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by x163209 on 22/02/2016.
 */
public class PersonaGeneralControlador {
    private static final Logger log = LogManager.getLogger();
    @FXML
    private TableView<Persona> tablaPersona;
    @FXML
    private TableColumn<Persona, String> columnaNombre;
    @FXML
    private TableColumn<Persona, String> columnaApellido;

    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidoLabel;
    @FXML
    private Label calleLabel;
    @FXML
    private Label codigoPostalLabel;
    @FXML
    private Label ciudadLabel;
    @FXML
    private Label nacimientoLabel;

    private TutorialJavaFX8Main main;

    @FXML
    private void initialize() {
        columnaNombre.setCellValueFactory(datoColumna ->  datoColumna.getValue().nombreProperty());
        columnaApellido.setCellValueFactory(datoColumna -> datoColumna.getValue().apellidoProperty());

        //Borramos los detalles de la persona
        mostrarDetallePersona(null);

        //Añadimos el oyente de cambios en la lista
        tablaPersona.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostrarDetallePersona(newValue));

    }

    private void mostrarDetallePersona(Persona persona) {
        if(persona!=null) {
            nombreLabel.setText(persona.getNombre());
            apellidoLabel.setText(persona.getApellido());
            calleLabel.setText(persona.getCalle());
            ciudadLabel.setText(persona.getCiudad());
            codigoPostalLabel.setText(Integer.toString(persona.getCodigoPostal()));
            nacimientoLabel.setText(DateTimeFormatter.ISO_LOCAL_DATE.format(persona.getNacimiento()));
        } else {
            nombreLabel.setText("");
            apellidoLabel.setText("");
            calleLabel.setText("");
            ciudadLabel.setText("");
            codigoPostalLabel.setText("");
            nacimientoLabel.setText("");
        }
    }

    @FXML
    private void manejadorBorradoPersona() {
        int selectedIndex = tablaPersona.getSelectionModel().getSelectedIndex();
        if(selectedIndex==-1) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setContentText("Debe seleccionar una persona para borrar");
            alerta.showAndWait();
        } else
            tablaPersona.getItems().remove(selectedIndex);
    }

    public TutorialJavaFX8Main getMain() {
        return main;
    }

    public void setMain(TutorialJavaFX8Main main) {
        this.main = main;

        //Añadir la lista a la tabla
        tablaPersona.setItems(main.getPersonas());
    }

    @FXML
    private void manejadorNuevaPersona() {
        try {
            Persona persona = new Persona();

            FXMLLoader cargadorFxml = new FXMLLoader();
            cargadorFxml.setLocation(getClass().getResource("/com/example/javawebstart/tutorialjavafx8/vista/EditarPersonaDialogo.fxml"));
            AnchorPane editarPersonaDialogo = cargadorFxml.load();

            Stage dialogo = new Stage();
            dialogo.setTitle("Nueva persona");
            dialogo.initModality(Modality.WINDOW_MODAL);
            dialogo.initOwner(main.getVentanaPrincipal());

            Scene ventanaDialogo = new Scene(editarPersonaDialogo);
            dialogo.setScene(ventanaDialogo);

            EditarPersonaContorlador controlador = cargadorFxml.getController();
            controlador.setPersona(persona);
            controlador.setVentanaDialogo(dialogo);

            dialogo.showAndWait();


        } catch (Exception e) {
            log.error("No se puede crear el dialogo de nueva persona", e);
        }
    }

    public TableView<Persona> getTablaPersona() {
        return tablaPersona;
    }

    public void setTablaPersona(TableView<Persona> tablaPersona) {
        this.tablaPersona = tablaPersona;
    }

    public TableColumn<Persona, String> getColumnaNombre() {
        return columnaNombre;
    }

    public void setColumnaNombre(TableColumn<Persona, String> columnaNombre) {
        this.columnaNombre = columnaNombre;
    }

    public TableColumn<Persona, String> getColumnaApellido() {
        return columnaApellido;
    }

    public void setColumnaApellido(TableColumn<Persona, String> columnaApellido) {
        this.columnaApellido = columnaApellido;
    }

    public Label getNombreLabel() {
        return nombreLabel;
    }

    public void setNombreLabel(Label nombreLabel) {
        this.nombreLabel = nombreLabel;
    }

    public Label getApellidoLabel() {
        return apellidoLabel;
    }

    public void setApellidoLabel(Label apellidoLabel) {
        this.apellidoLabel = apellidoLabel;
    }

    public Label getCalleLabel() {
        return calleLabel;
    }

    public void setCalleLabel(Label calleLabel) {
        this.calleLabel = calleLabel;
    }

    public Label getCodigoPostalLabel() {
        return codigoPostalLabel;
    }

    public void setCodigoPostalLabel(Label codigoPostalLabel) {
        this.codigoPostalLabel = codigoPostalLabel;
    }

    public Label getCiudadLabel() {
        return ciudadLabel;
    }

    public void setCiudadLabel(Label ciudadLabel) {
        this.ciudadLabel = ciudadLabel;
    }

    public Label getNacimientoLabel() {
        return nacimientoLabel;
    }

    public void setNacimientoLabel(Label nacimientoLabel) {
        this.nacimientoLabel = nacimientoLabel;
    }

}
