/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ControladorBaseDatos;
import modelo.Persona;
import vista.IntefazAgendaContactos;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Capa Brothers
 */
public class Controlador implements ActionListener {

    private IntefazAgendaContactos view;
    private Persona persona;
    private ControladorBaseDatos base;

    ArrayList<Persona> todasPersonas;
    int indiceActual = 0;
    int totalResultados = 0;

    public Controlador(IntefazAgendaContactos view, Persona persona, ControladorBaseDatos base) {
        this.view = view;
        this.persona = persona;
        this.base = base;
        this.view.txtNumeroActual.setText(String.valueOf(indiceActual));
        this.view.txtNumeroSiguiente.setText(String.valueOf(totalResultados));

        this.view.btnAgregar.addActionListener(this);
        this.view.btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        this.view.btnPresentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPresentarActionPerformed(evt);
            }
        });
        this.view.btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarAnterior(evt);
            }
        });
        this.view.btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarSiguiente(evt);
            }
        });
    }

    public void iniciar() {
        view.setTitle("Agenda Contactos");
        view.setLocationRelativeTo(null);
        
    }

    public void actionPerformed(ActionEvent e) {
        base.ControladorBadedeDatos();
        persona.setId(view.txtId.getText());
        persona.setNombre(view.txtNombre.getText());
        persona.setApellido(view.txtApellido.getText());
        persona.setEmail(view.txtEmail.getText());
        persona.setTelefono(view.txtTelefono.getText());

        try {
            base.agregarPersona(persona);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void btnBuscarActionPerformed(ActionEvent e) {
        base.ControladorBadedeDatos();
        String apellido = view.txtBuscarApellido.getText();
        todasPersonas = base.getPersonasPorApellido(apellido);
        Persona persona = todasPersonas.get(indiceActual);
        setTextos(persona, indiceActual, todasPersonas.size());
    }

    public void btnPresentarActionPerformed(ActionEvent e) {
        base.ControladorBadedeDatos();
        todasPersonas = base.getPersonas();
        Persona persona = todasPersonas.get(indiceActual);
        setTextos(persona, indiceActual, todasPersonas.size());
    }

    public void btnMostrarAnterior(ActionEvent e) {
        int count = indiceActual - 1;
        if (count >= 0) {
            Persona persona = todasPersonas.get(count);
            setTextos(persona, count, totalResultados);
            indiceActual = indiceActual - 1;
        } else {
            System.out.println("fin de la lista");
        }
    }

    public void btnMostrarSiguiente(ActionEvent e) {
        int count = indiceActual + 1;
        totalResultados = todasPersonas.size();
        if (count < totalResultados) {
            Persona persona = todasPersonas.get(count);
            setTextos(persona, count, totalResultados);
            indiceActual = indiceActual + 1;
        } else {
            System.out.println("fin de la lista");
        }
    }

    public void setTextos(Persona persona, int indiceActual, int totalResultados) {
        view.txtId.setText(persona.getId());
        view.txtNombre.setText(persona.getNombre());
        view.txtApellido.setText(persona.getApellido());
        view.txtEmail.setText(persona.getEmail());
        view.txtTelefono.setText(persona.getTelefono());
        view.txtNumeroActual.setText(String.valueOf(indiceActual + 1));
        view.txtNumeroSiguiente.setText(String.valueOf(totalResultados));
    }

}
