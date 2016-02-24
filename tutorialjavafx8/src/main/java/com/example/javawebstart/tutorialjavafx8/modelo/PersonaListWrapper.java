package com.example.javawebstart.tutorialjavafx8.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by x163209 on 24/02/2016.
 */
@XmlRootElement(name = "personas")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonaListWrapper {

    @XmlElement(name = "persona")
    private List<Persona> personas;

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
}
