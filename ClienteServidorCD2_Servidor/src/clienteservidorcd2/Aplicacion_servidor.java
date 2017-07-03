package clienteservidorcd2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Aplicacion_servidor {

    public static int i;

    public static void main(String[] args) {

        /*if (args.length != 1) {
            System.err.println("Usage: java KnockKnockServer <port number>");
            System.exit(1);
        }*/
        int portNumber = 5000;
        int contador = 0;

        try {

            ServerSocket serverSocket = new ServerSocket(portNumber);
            for (i = 0; i < 200; i++) {

                Socket clientSocket = serverSocket.accept();
                //String direccion;
                //StringTokenizer tokens = new StringTokenizer(clientSocket.getRemoteSocketAddress().toString(), ":");
                //direccion = tokens.nextToken();
                //direccion = direccion.replace("/", "");
                String usuario = "";
                String passw = "";
                String mensaje = "";
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine, outputLine;
                outputLine = "Ingrese el nombre de usuario";
                out.println(outputLine);
                usuario = in.readLine();
                outputLine = "Ingrese la contrasena";
                out.println(outputLine);
                passw = in.readLine();
                ArrayList <String> usuarios;
                ArrayList <String> pass;
                ArrayList <String> facultad;
                usuarios = usuarios();
                pass = pass();
                facultad = facultad();
                for (int i = 0; i < usuarios().size(); i++) {
                    if (usuarios.get(i).equals(usuario)) {
                        if (passw.equals(pass.get(i))) {
                            System.out.println("Se permite la conexion");
                            contador++;
                            System.out.println("Cliente conectado  " + i);
                            Thread hilo = new Thread(new MyThread_servidor(clientSocket, contador,Integer.parseInt(facultad.get(i))));
                            hilo.start();
                            break;
                        }
                    }
                    else{
                        System.out.println("No se permite la conexion");
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());

        } catch (Exception ex) {
            Logger.getLogger(Aplicacion_servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String leerArchivo() throws IOException {
        File archivo;
        FileReader fr;
        BufferedReader br;
        String linea;
        String aux = "";

        try {
            archivo = new File("hosts.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                aux += linea + "\n";
            }
            br.close();
            fr.close();

        } catch (IOException e) {
        }

        //System.out.println(aux);
        return aux;
    }

    public static ArrayList usuarios() throws Exception {
        Connection conect;
        ArrayList usuarios = new ArrayList();
        Statement st;
        ResultSet rs;
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Universidad;user=sa; password=Bryan123;";
        conect = DriverManager.getConnection(connectionUrl);
        st = conect.createStatement();
        rs = st.executeQuery("select usuario from persona");
        while (rs.next()) {
            usuarios.add(rs.getString(1));
        }
        return usuarios;
    }

    public static ArrayList pass() throws Exception {
        Connection conect;
        ArrayList pass = new ArrayList();
        Statement st;
        ResultSet rs;
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Universidad;user=sa; password=Bryan123;";
        conect = DriverManager.getConnection(connectionUrl);
        st = conect.createStatement();
        rs = st.executeQuery("select password from persona");
        while (rs.next()) {
            pass.add(rs.getString(1));
        }
        return pass;
    }
    public static ArrayList facultad() throws Exception {
        Connection conect;
        ArrayList facultad = new ArrayList();
        Statement st;
        ResultSet rs;
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Universidad;user=sa; password=Bryan123;";
        conect = DriverManager.getConnection(connectionUrl);
        st = conect.createStatement();
        rs = st.executeQuery("select facultad from persona");
        while (rs.next()) {
            facultad.add(rs.getString(1));
        }
        return facultad;
    }

}
