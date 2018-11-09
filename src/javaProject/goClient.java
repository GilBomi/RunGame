package javaProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class goClient extends Thread{
	private char me,other;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public goClient() throws UnknownHostException,IOException {
		socket=new Socket("localhost",9001);
		input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output=new PrintWriter(socket.getOutputStream(),true);
		
	}
	public void run() {
		
	}
}
