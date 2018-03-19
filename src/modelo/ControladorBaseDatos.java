/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.List;

/**
 *
 * @author Capa Brothers
 */
public class ControladorBaseDatos {

    String url;
    String Usuario;
    String clave;
    Connection conexion;
    PreparedStatement seleccionarPersonas;
    PreparedStatement seleccionarPersonasPorApellido;
    PreparedStatement insertarnuevaPersona;

    public void ControladorBadedeDatos() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Driver javaDB cargado");
        } catch (ClassNotFoundException es) {
            System.out.println("Error no encuentra la clase");
        }
        conexion = null;
        try {
            url = "jdbc:derby://localhost:1527//Agenda";
            conexion = DriverManager.getConnection(url, "agenda", "agenda");
            System.out.println("Conexion establecida a la Base de Datos");
        } catch (SQLException ex) {
            System.out.println("No se pudo establecer conexion a la ase de datos");
        }
    }

    public void agregarPersona(Persona persona) throws SQLException {

        try {
            Statement stm = conexion.createStatement();
            insertarnuevaPersona = conexion.prepareStatement("INSERT INTO CONTACTOS (id,nombre,apellido,email,telefono) VALUES(?,?,?,?,?)");
            insertarnuevaPersona.setString(1, persona.getId());
            insertarnuevaPersona.setString(2, persona.getNombre());
            insertarnuevaPersona.setString(3, persona.getApellido());
            insertarnuevaPersona.setString(4, persona.getEmail());
            insertarnuevaPersona.setString(5, persona.getTelefono());
            insertarnuevaPersona.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos ingresados Correctamente", "Ingreso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            System.out.println("Agregar Persona " + ex);
        }
    }

    public ArrayList getPersonasPorApellido(String apellido) {
        ArrayList<Persona> encontrado = new ArrayList<Persona>();
        try {
            String query = "Select * from CONTACTOS where LOWER(apellido) LIKE LOWER('" + apellido + "%')";
            seleccionarPersonasPorApellido = conexion.prepareStatement(query);
            ResultSet resultado = seleccionarPersonasPorApellido.executeQuery();
            if (resultado.next() == true) {
                Persona personaEncontrada = new Persona();
                String id = resultado.getString("id");
                String nb = resultado.getString("nombre");
                String ap = resultado.getString("apellido");
                String email = resultado.getString("email");
                String tlf = resultado.getString("telefono");
                personaEncontrada.setId(id);
                personaEncontrada.setNombre(nb);
                personaEncontrada.setApellido(ap);
                personaEncontrada.setEmail(email);
                personaEncontrada.setTelefono(tlf);
                encontrado.add(personaEncontrada);

            } else {
                System.out.println("No existe");
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta" + ex);
        }
        return encontrado;
    }

    public ArrayList getPersonas() {
        ArrayList<Persona> personas = new ArrayList<Persona>();
        try {
            String query = "Select id , nombre ,apellido, email ,telefono from contactos";
            seleccionarPersonasPorApellido = conexion.prepareStatement(query);
            ResultSet resultado = seleccionarPersonasPorApellido.executeQuery();
            while (resultado.next()) {
                Persona personaEncontrada = new Persona();
                String id = resultado.getString("id");
                String nb = resultado.getString("nombre");
                String ap = resultado.getString("apellido");
                String email = resultado.getString("email");
                String tlf = resultado.getString("telefono");
                personaEncontrada.setId(id);
                personaEncontrada.setNombre(nb);
                personaEncontrada.setApellido(ap);
                personaEncontrada.setEmail(email);
                personaEncontrada.setTelefono(tlf);
                personas.add(personaEncontrada);
            }

            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta" + ex);
        }
        return personas;
    }

}
