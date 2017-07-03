/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author KEVIN
 */
public class Conexion {
    private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    private String login = "test";
    private String password = "test";
    private Connection conexion=null;
    
    public Conexion(){
        try {
        Class.forName("oracle.jdbc.OracleDriver");                 
        conexion = DriverManager.getConnection(url,login,password);            
        if (conexion != null) {
            System.out.println("Conexion exitosa!");
        } else {
            System.out.println("Conexion fallida!");
        }
        } catch (Exception e) {
        e.printStackTrace();
        }     
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
    public void Desconectar() throws SQLException{
        System.out.println(" cerrando ");
        //conexion.close();        
        conexion=null;
    }
    
}
