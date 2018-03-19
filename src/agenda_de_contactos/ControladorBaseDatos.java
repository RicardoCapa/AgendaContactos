/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda_de_contactos;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.getopt.GetOpt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Capa Brothers
 */
public class ControladorBaseDatos {

    private Persona persona;

    String url = "";
    String Usuario;
    String clave;
    Connection conexion;
    PreparedStatement seleccionarPersonas;
    PreparedStatement seleccionarPersonasporApellido;
    PreparedStatement insertarnuevaPersona;

    public static void ControladorBadedeDatos() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Driver javaDB cargado");
        } catch (ClassNotFoundException es) {
            System.out.println("Error no encuentra la clase");
        }
        Connection conexion = null;
        try {
            String url = "jdbc:derby://localhost:1527//Agenda";
            conexion = DriverManager.getConnection(url, "agenda", "agenda");
            System.out.println("Conexion establecida a la Base de Datos");
        } catch (SQLException ex) {
            System.out.println("No se pudo establecer conexion a la ase de datos");
        }
    }

    public static void agregarPersona(Persona persona) throws SQLException {
        String url = "";
        String Usuario;
        String clave;
        Connection conexion =null;
        PreparedStatement seleccionarPersonas;
        PreparedStatement seleccionarPersonasporApellido;
        PreparedStatement insertarnuevaPersona;
        try {
            Statement stm = conexion.createStatement();
            insertarnuevaPersona = 
                    conexion.prepareStatement("INSERT INTO CONTACTOS VALUES(id,nombre,email,telefono)" + "values(?,?,?,?)");
            insertarnuevaPersona.setString(1, persona.getId());
            insertarnuevaPersona.setString(2, persona.getId());
            insertarnuevaPersona.setString(3, persona.getId());
            insertarnuevaPersona.setString(4, persona.getId());
            insertarnuevaPersona.execute();
            System.out.println("datos ingresados correctamente");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void hola() {

    }

}
