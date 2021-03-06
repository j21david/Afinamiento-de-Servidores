/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Conexion.Conexion;
import Login.Ingreso;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jairo
 */
public class MiConsola {
    
    
    Ingreso inp = new Ingreso();
    
    public void record(String ip,int id){
        try {
            FileWriter miLog = new FileWriter("src/miLog.txt",true);                       
            //String miFree=exeComando("free");
            //String miUpTime=exeComando("uptime");
            String hora=new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            String finalog="Usuario "+id+":\t"+ip+"\nHora de Ingreso:\t"+hora+"\n";
            miLog.append(finalog);
         //   System.out.println(finalog);
         miLog.close();

            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KnockKnockServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(KnockKnockServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KnockKnockServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void saveUserdata(String userInput,int id) {
          try { 
              FileWriter fw = new FileWriter(id+".txt");
              fw.write(userInput);
              fw.close();
              
          } catch (IOException ex) {
              Logger.getLogger(MiConsola.class.getName()).log(Level.SEVERE, null, ex);
          }
       
    }
    
    String cesar(String usrInput, int delta) throws SQLException{
        
        //int delta=inp.getIDpersona(afinamiento.afinamiento.getUser());
        
        char [] encripto = usrInput.toCharArray();
        for (int i = 0; i < encripto.length; i++) {
            encripto[i]=(char) (encripto[i]+delta);
        }        
        return new String(encripto);        
    }

    public boolean validarUser(String userIP) {
       BufferedReader br = null;
          try {
              br = new BufferedReader(new FileReader("acceso.txt"));
              String currentIp;
              while ((currentIp = br.readLine()) != null) {
                  if(currentIp.equals(userIP)){
                  System.out.println("Se ha otorgado acceso a:\t"+userIP);
                  return true;
                  }                      
              }
              
          } catch (FileNotFoundException ex) {
              Logger.getLogger(MiConsola.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
              Logger.getLogger(MiConsola.class.getName()).log(Level.SEVERE, null, ex);
          } finally {
              try {
                  br.close();
              } catch (IOException ex) {
                  Logger.getLogger(MiConsola.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
           System.out.println("Se ha denegado acceso a:\t"+userIP);
          return false;

    }
    
    
}
