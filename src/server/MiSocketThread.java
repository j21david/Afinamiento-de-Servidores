/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

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
                //consola.saveUserdata(input,id);
                //String inputLine, outputLine;
                String cifrado= consola.cesar(input);
                out.println(cifrado);                   
            }
            else{
                out.println("Acceso Denegado");                
            }
            
            // Initiate conversation with client
          /*  KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }*/
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
    
    
}
