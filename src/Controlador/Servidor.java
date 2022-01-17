package Controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {


	// declaring required variables
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static InputStreamReader inputStreamReader;
	private static OutputStreamWriter outputStreamWriter;
	private static BufferedReader bufferedReader;
	private static String message="";
	private static Integer opcion;
	private static modelo.envio envio;
	
	  
	public static void iniciar() {
	  
	    try {
	        // creating a new ServerSocket at port 4444
	        serverSocket = new ServerSocket(4444); 
	  
	    } catch (IOException e) {
	        System.out.println("Could not listen on port: 4444");
	    }
	  
	    System.out.println("Server started. Listening to the port 4444");
	  
	    // we keep listening to the socket's 
	      // input stream until the message
	    // "over" is encountered
	    while (!message.equalsIgnoreCase("over")) {
	        try {
	  
	            // the accept method waits for a new client connection
	            // and and returns a individual socket for that connection
	            clientSocket = serverSocket.accept(); 
	            
	            // get the inputstream from socket, which will have 
	              // the message from the clients
	            inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
	            outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream()); 
	            bufferedReader = new BufferedReader(inputStreamReader);                     
	             
	              // reading the message
	            message = bufferedReader.readLine();
	  
	            // printing the message
	            System.out.println(message);
	            
	            
	            String[] peticion = message.split("\\/");            
	            
	            opcion = Integer.parseInt(peticion[0]);
	            
	            switch(opcion) {
	            case 0:
	            	envio = new modelo.envio();
	            	envio.setLogin(comprobarUsuario(peticion[1], peticion[2]));
	            	outputStreamWriter.write(envio);
	            	outputStreamWriter.flush();
	            }
	              
	            // finally it is very important
	              // that you close the sockets
	            inputStreamReader.close();
	            outputStreamWriter.close();
	            clientSocket.close();
	  
	        } catch (IOException ex) {
	            System.out.println("Problem in message reading");
	        }
	     }
	  }


	private static Boolean comprobarUsuario(String peticion, String peticion2) {
		// TODO Auto-generated method stub
		if (baseDeDatos.Consultas.consultarUsuario(peticion, peticion2)) {
			 return true;
			  
		} else {
			return false;
		}
		
	}

}
