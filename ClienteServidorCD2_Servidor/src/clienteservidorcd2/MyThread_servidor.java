package clienteservidorcd2;

import static clienteservidorcd2.Aplicacion_servidor.i;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThread_servidor implements Runnable {

    Socket clientSocket;
    int contador;
    int facultad;

    public MyThread_servidor(Socket clientSocket, int contador,int facultad) {
        this.clientSocket = clientSocket;
        this.contador = contador;
        this.facultad = facultad;

    }

    @Override
    public void run() {
        PrintWriter out;
        try {
            String usuario = "";
            String mensaje = "";
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String inputLine, outputLine;
                outputLine = "Ingrese el mensaje";
                out.println(outputLine);
                mensaje = in.readLine();
                escribirArchivo(mensaje);
                in.close();
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(MyThread_servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            i--;

        } catch (IOException ex) {
            Logger.getLogger(MyThread_servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(MyThread_servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print(i);
    }

    /*public void escribirArchivo(String estado) throws IOException {
        Date date = new Date();
        File f = new File("a.log");
        FileWriter w = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);
        wr.write("\r\nCliente " + contador + ": " + estado + "..... " + clientSocket.getLocalAddress().getHostAddress() + "\r\nhora: " + date.toString());
        wr.close();
        bw.close();
    }*/
    public void escribirArchivo(String texto) throws IOException {
        Date date = new Date();
        File f = new File(contador + ".txt");
        FileWriter w = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);
        wr.write("\r\n" + texto + "\r\n" + cesar(texto,facultad));
        wr.close();
        bw.close();
    }

    public String cesar(String texto,int salto) {
        String resultado = "";
        char aux[];
        aux = texto.toCharArray();
        for (int i = 0; i < aux.length; i++) {
            resultado += (char) (((int) aux[i]) + salto*4);
        }
        return resultado;

    }

}
