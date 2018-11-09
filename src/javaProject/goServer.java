package javaProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class goServer {
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ServerSocket ss=new ServerSocket(9001);
		try {
			while(true) {
				Play play=new Play();
				Player player1=new Player(play,ss.accept(),);
				Player player2=new Player(play,ss.accept(),);
				
				player1.setOther(player2);
				player2=setOther(player1);
				
				player1.start();
				player2.start();
			}
		} finally {
			ss.close();
		}
	}

}
class Play {
	
}
class Player extends Thread {
	Play play;
	Socket socket;
	BufferedReader input;
	PrintWriter output;
	char playerName;
	Player other;
	
	public Player(Play play,Socket socket, char playerName) {
		this.play=play;
		this.socket=socket;
		this.playerName=playerName;
		
		try {
			input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output=new PrintWriter(socket.getOutputStream(),true);
		} catch(IOException e) {
			
		}
	}
	public void setOther(Player other) {
		this.other=other;
	}
	public void run() {
		
	}
}

