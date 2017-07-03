/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author jairo
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));

    }
    
}
