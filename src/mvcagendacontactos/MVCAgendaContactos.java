/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcagendacontactos;

import controlador.Controlador;
import modelo.ControladorBaseDatos;
import modelo.Persona;
import vista.IntefazAgendaContactos;

/**
 *
 * @author Capa Brothers
 */
public class MVCAgendaContactos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControladorBaseDatos mod = new ControladorBaseDatos();
        Persona per = new Persona();
        IntefazAgendaContactos interfaz = new IntefazAgendaContactos();
        Controlador ctrl = new Controlador(interfaz, per, mod);
        ctrl.iniciar();
        interfaz.setVisible(true);
    }
    
}
