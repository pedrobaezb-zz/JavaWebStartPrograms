package com.example.javawebstart.tutorialjavafx8;

import com.example.javawebstart.tutorialjavafx8.modelo.Persona;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class EditarPersonaContorlador {
    private static final Logger log = LogManager.getLogger();
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField calle;
    @FXML
    private TextField ciudad;
    @FXML
    private TextField codigoPostal;
    @FXML
    private TextField nacimiento;

    private Stage ventanaDialogo;
    private Persona persona;
    private boolean okPulsado=false;

    @FXML
    private void initialize() {

    }

    public void setVentanaDialogo(Stage ventanaDialogo) {
        this.ventanaDialogo = ventanaDialogo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;

        nombre.setText(persona.getNombre());
        apellido.setText(persona.getApellido());
        calle.setText(persona.getCalle());
        ciudad.setText(persona.getCiudad());
        codigoPostal.setText(Integer.toString(persona.getCodigoPostal()));
        if(persona.getNacimiento()!=null)
            nacimiento.setText(DateTimeFormatter.ISO_DATE.format(persona.getNacimiento()));
        nacimiento.setPromptText("yyy-mm-dd");
    }

    public boolean isOkPulsado() {
        return okPulsado;
    }

    @FXML
    private void manejadorOk () {
        try {
            if (formularioValido()) {
                persona.setNombre(nombre.getText());
                persona.setApellido(apellido.getText());
                persona.setCalle(calle.getText());
                persona.setCiudad(ciudad.getText());
                persona.setCodigoPostal(Integer.parseInt(codigoPostal.getText()));
                persona.setNacimiento(LocalDate.parse(nacimiento.getText()));

                okPulsado = true;
                ventanaDialogo.close();
            }
        } catch (Exception e) {
            log.error("No se ha podido grabar", e);
        }
    }

    @FXML
    private void manejadorCancelar () {
        ventanaDialogo.close();
    }

    private boolean formularioValido () {
        boolean valido=false;

        ArrayList<String> errores = new ArrayList<>();

        if(StringUtils.isNoneBlank(
                nombre.getText(),
                apellido.getText(),
                calle.getText(),
                ciudad.getText(),
                codigoPostal.getText(),
                nacimiento.getText()
        ) ) {
            if(!StringUtils.isNumeric(codigoPostal.getText()))
                errores.add("El codigo postal debe ser un numero");
            try {
                LocalDate.parse(nacimiento.getText());
            } catch (DateTimeParseException de) {
                errores.add("La fecha esta en formato incorrecto (yyyy-mm-dd)");
            }

        } else {
            errores.add("Todos los campos deben estar rellenos");
        }

        if(errores.size()>0) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Errores de validacion");

            alerta.setContentText("Errores encontrados: " + errores.toString());
            alerta.showAndWait();
        } else
            valido=true;


        return valido;
    }
}
