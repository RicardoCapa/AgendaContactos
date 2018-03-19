/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Capa Brothers
 */
public class Persona {

    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private String apellido;
    static ArrayList<Persona> personas = new ArrayList<Persona>();

    public Persona() {
    }

    public Persona(String id, String nombre, String email, String telefono, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public static void setPersonaToArrayList(Persona persona) {
        personas.add(persona);
    }

    public static  ArrayList getPersonaArray() {
        return personas;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + ", apellido=" + apellido + '}';
    }

}
