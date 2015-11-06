/*
 * To change this template, choose Tools | Templates * and open the template in the editor.
 */
package proyecto2;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author UTN
 */
public class ConexionPostgres {

    private Connection conn; //Se Crea Variable "Conexión" con Base de Datos

    //Se Crea el Método Constructor
    public ConexionPostgres(String pNombreDB, String pUser, String dPassword) throws ClassNotFoundException, SQLException {
        try {
            String driver = "org.postgresql.Driver"; //Se Asigna el Driver de "Conexión" con Base de Datos
            Class.forName(driver);
            String connString = "jdbc:postgresql://localhost:5432/" + pNombreDB; //String de Conexión
            String user = pUser; //Usuario de PostGreSQL 9.2
            String password = dPassword; //Constraseña de Usuario del PostGreS

            this.conn = DriverManager.getConnection(connString, user, password); //Se Realiza la Conexión
            System.out.println("Conexión Realizada con Éxito");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public ResultSet select(String pCampos, String pTabla, String pCondicion) {
        ResultSet rs = null;//obtener los datos del select
        Statement s = null;//se utiliza para inicializar la conexión
        String sentencia = "";
        try {
            s = this.conn.createStatement();//se inicializa la conexión
            sentencia = " select " + pCampos + " from " + pTabla;//se crea el select
            rs = s.executeQuery(sentencia);//se ejecuta y se obtienen los datos
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return (rs);
    }

    public String insertar(String pTabla, String pValores) {
        Statement s = null;
        String sentencia = "";
        String retorno = "Datos Insertados";
        try {
            s = this.conn.createStatement();
            sentencia = "insert into " + pTabla + " values " + pValores;
            s.executeQuery(sentencia);
        } catch (SQLException e) {
            System.err.println(e.toString());
            retorno = e.toString();
        }
        return (retorno);
    }

    public String modificar(String pTabla, String pValores) {
        String sentencia = "";
        String retorno = "Datos Modificados";
        Statement s = null;
        try {
            s = this.conn.createStatement();
            sentencia = " update " + pTabla + " " + pValores;
            s.executeQuery(sentencia);
        } catch (Exception e) {
            retorno = e.toString();
        }
        return retorno;
    }
    
    public String borrar(String pTabla, String pValores){
    String sentencia = "";
    String retorno = "Datos Modificados";
    Statement s = null;
    try{
    s = this.conn.createStatement();
    sentencia = "delete from "+ pTabla+pValores;
    s.executeQuery(sentencia);
    }
    catch(Exception e){
    retorno = e.toString();
    }
    return retorno;
    }
}