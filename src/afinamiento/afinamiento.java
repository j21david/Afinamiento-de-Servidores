/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afinamiento;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;



/**
 *
 * @author jairo
 */
public class afinamiento {
    
    
     public static void main(String[] args)  {
         
         
        // miniThread mini = new miniThread();
        // mini.start();
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService ex = Executors.newCachedThreadPool();        
               
        for(int i=0;i<10;i++){
                
            
                ex.submit(new miniThread(latch,i));
             
           // System.out.println("client "+i);
            
        }
        //latch.countDown();        
        while (!ex.isTerminated()) {
           // System.out.println("hack :"+((ThreadPoolExecutor) ex).getActiveCount());
        }
        System.out.println("\nFin Pruebas de Estress");                    
    }
    
}
