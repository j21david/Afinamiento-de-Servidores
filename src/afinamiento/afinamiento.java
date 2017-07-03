/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afinamiento;

import Login.Ingreso;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import server.MiConsola;



/**
 *
 * @author jairo
 */
public class afinamiento {
    
    
    
     public static void main(String[] args) throws SQLException  {
        Scanner entradaTeclado = new Scanner(System.in);
        Ingreso login = new Ingreso();        
        String user="",pass="";
        System.out.println("USUARIO: ");
        user=entradaTeclado.nextLine();                
        System.out.println("CONTRASENA: ");
        pass=entradaTeclado.nextLine();        
        
         //System.out.println(getUser());
        
        
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService ex = Executors.newCachedThreadPool();        
        
        
        if (login.validatePassAndUser(login.BuscarUsuario(user),login.BuscarPassword(user))) {   
            
            
            for(int i=0;i<10;i++){                            
                ex.submit(new miniThread(latch,i,login.getIDpersona(user)));             
           // System.out.println("client "+i);
            }
            //latch.countDown();                                
        }else{
            while (!ex.isTerminated()) {
           // System.out.println("hack :"+((ThreadPoolExecutor) ex).getActiveCount());
            }
            System.out.println("\nFin Pruebas de Estress");
        }
                            
    }
    
     public int getNumber(){
         int id;
         
         
         return 0;
     }
         
}
