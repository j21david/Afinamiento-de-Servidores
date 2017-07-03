/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afinamiento;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jairo
 */
public class miniThread extends Thread{

    CountDownLatch latch ;
    int id;
    int idUsr;
    miniThread(CountDownLatch latch,int id, int idUsr) {
        this.latch = latch;
        this.id=id;
        this.idUsr=idUsr;
    }

    @Override
    public void run() {
      
        String msj = "Hola soy thread"+this.id;
        String msjID = ""+this.idUsr;
        String hostName = "127.0.0.1";
        int portNumber = Integer.parseInt("4200");
        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        ) {                       
          
            out.println(msj);
            out.println(msjID);
            String server= in.readLine();
            
            FileWriter miLog = new FileWriter("src/clientesCesar/msj"+this.id,true);
            miLog.write(server);
            miLog.close();
            System.out.println("Memori :"+this.id);
            
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
        
    }

    
    
    
}
