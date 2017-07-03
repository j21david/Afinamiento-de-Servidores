/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Conexion.Conexion;
import java.io.Console;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


/**
 *
 * @author KEVIN
 */
public class Ingreso {

    String search;  
    PreparedStatement stm = null;
    Conexion enlace = new Conexion();
    ResultSet rs = null;
    
    
   
    public String BuscarUsuario(String usr) throws SQLException{
        String usuario="";
        search="Select * from Persona where NombreUsuario=?";
        stm=enlace.getConexion().prepareStatement(search);        
        stm.setString(1, usr);
        rs = stm.executeQuery();
        if (rs.next()){                        
            usuario=rs.getString(4);            
             //System.out.println(usuario);
        }                
        return usuario;
        //System.out.println(usuario);
    }
    
    public String BuscarPassword(String usr) throws SQLException{
        String contrasena="";
        search="Select * from Persona where NombreUsuario=?";
        stm=enlace.getConexion().prepareStatement(search);        
        stm.setString(1, usr);
        rs = stm.executeQuery();
        if (rs.next()) {                        
            contrasena=rs.getString(5);            
        }                
        return contrasena;
        //System.out.println(contrasena);
    }
    
    public int getIDpersona(String usr) throws SQLException{
        String id="";
        search="Select * from Persona where NombreUsuario=?";
        stm=enlace.getConexion().prepareStatement(search);        
        stm.setString(1, usr);
        rs = stm.executeQuery();
        if (rs.next()) {                        
            id=rs.getString(1);            
        }                                
        return Integer.parseInt(id);
    }
    public boolean validatePassAndUser(String usr,String pasword) throws SQLException{        
        boolean flag=false;    
        
        //System.out.println("hola");
        if ((BuscarUsuario(usr).equals(usr))&&(BuscarPassword(usr).equals(pasword))){            
            System.out.println("AUTORIZADO");
            //System.out.println("Hash "+BuscarPassword(usr).hashCode());
            flag=true;
            return flag;
        }else{
            System.out.println("Denegado");            
            return flag;
        }
    }
 
   
    
    public static void main(String[] args) throws SQLException {
        
       
    }
}



