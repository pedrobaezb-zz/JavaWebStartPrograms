package com.example.javawebstart.tutorialjavafx8.modelo;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Created by x163209 on 22/02/2016.
 */
public class Persona {
    private StringProperty nombre = new SimpleStringProperty();
    private StringProperty apellido = new SimpleStringProperty();
    private StringProperty calle = new SimpleStringProperty();
    private IntegerProperty codigoPostal = new SimpleIntegerProperty();
    private StringProperty ciudad = new SimpleStringProperty();
    private ObjectProperty<LocalDate> nacimiento = new SimpleObjectProperty<LocalDate>();

    public Persona() {
    }

    public Persona(String nombre, String apellido) {
        this.nombre.set(nombre);
        this.apellido.set(apellido);

        // Some initial dummy data, just for convenient testing.
        this.calle = new SimpleStringProperty("Calle prueba");
        this.codigoPostal = new SimpleIntegerProperty(1234);
        this.ciudad = new SimpleStringProperty("Ciudad Prueba");
        this.nacimiento = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre=" + nombre +
                ", apellido=" + apellido +
                ", calle=" + calle +
                ", codigoPostal=" + codigoPostal +
                ", ciudad=" + ciudad +
                ", nacimiento=" + nacimiento +
                '}';
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getCalle() {
        return calle.get();
    }

    public StringProperty calleProperty() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle.set(calle);
    }

    public int getCodigoPostal() {
        return codigoPostal.get();
    }

    public IntegerProperty codigoPostalProperty() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal.set(codigoPostal);
    }

    public String getCiudad() {
        return ciudad.get();
    }

    public StringProperty ciudadProperty() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad.set(ciudad);
    }

    public LocalDate getNacimiento() {
        return nacimiento.get();
    }

    public ObjectProperty<LocalDate> nacimientoProperty() {
        return nacimiento;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento.set(nacimiento);
    }
}
