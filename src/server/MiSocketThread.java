/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Login.Ingreso;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jairo
 */
public class MiSocketThread  implements Runnable{

    int id;
    MiConsola consola ;   
    Socket clientSocket;
    

    public MiSocketThread(Socket socket,int id) {
        this.clientSocket = socket;
        this.id=id;
    }
    
    
    @Override
    public void run() {
          try  {
            consola=new MiConsola();              
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);           
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));           
            if( consola.validarUser(clientSocket.getLocalAddress().toString())){
                consola.record(clientSocket.getLocalAddress().toString(),id);
                String input=in.readLine();
                String IdUsr=in.readLine();
                //System.out.println(input);
                
                String cifrado = consola.cesar(input,Integer.parseInt(IdUsr));
                //System.out.println("--> "+cifrado);
                out.println(cifrado);                   
            }
            else{
                out.println("Acceso Denegado");                
            }
            
       
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + " or listening for a connection");
            System.out.println(e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(MiSocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
