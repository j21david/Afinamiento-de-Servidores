
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class KnockKnockServer {
    public static void main(String[] args) throws IOException {
        
      /*  if (args.length != 1) {
            System.err.println("Usage: java KnockKnockServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);*/
        int portNumber = Integer.parseInt("4200");
        int i=0;
        int maxClient=10;
        try{
            final ExecutorService service = Executors.newCachedThreadPool();
            ServerSocket serversock=new ServerSocket(portNumber);
            while(true){
                int activeClients= ((ThreadPoolExecutor) service).getActiveCount();              
                if(activeClients<=maxClient){
                Socket  socket=serversock.accept();
                service.submit(new MiSocketThread(socket,i));
                i++;
                }
                System.out.println("Clientes activos: "+activeClients);                 
            }
        }catch(IOException e){}                    
    }
    
   
}