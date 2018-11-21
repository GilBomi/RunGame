package javaProject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;

public class goServer {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		/*ServerSocket ss=new ServerSocket(9001);
		System.out.println("1");
		try {
			while(true) {
				System.out.println("2");
				Player player1=new Player(ss.accept());
				Player player2=new Player(ss.accept());
				player1.setOther(player2);
				player2.setOther(player1);
				player1.start();
				player2.start();
			}
		} finally {
			ss.close();
		}*/
		ServerSocket ss=new ServerSocket(9002);
		System.out.println("서버 시작되었습니다.");
		try {
			Player player1=new Player(ss.accept());
			Player player2=new Player(ss.accept());
			player1.setOther(player2);
			player2.setOther(player1);
			player1.start();
			player2.start();
			System.out.println("페어 만들어짐");
		} finally {
			ss.close();
		}
	}
}

class Player extends Thread {
	/*Socket socket;
	private ObjectInputStream input; // 바이트 읽어오기
	private ObjectOutputStream output; // 사진 보내기
	private DataInputStream in; // 기초 자료형 읽기
	private DataOutputStream out; 

	private String goName;
	private JLabel myName;
	private Image myFace;
	private int length=0;
	Player other;

	public Player(Socket socket) {
		this.socket=socket;

		try {
			input=new ObjectInputStream(socket.getInputStream());
			output=new ObjectOutputStream (socket.getOutputStream());

			myFace=(Image)input.readObject(); // 이미지
			goName=in.readUTF(); // 이름
			length=in.readInt(); // 움직인 총 거리

			myName=new JLabel(goName);
		} catch(IOException e) {
			System.out.println("2");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("3");
		}
	}
	public void setOther(Player other) {
		this.other=other;
	}
	public void run() {
		try {
			other.output.writeObject(myFace);
			other.out.writeUTF(goName);
			while(true) {
				other.out.writeInt(length);
			}
		} catch(IOException e) {
			System.out.println("4");
		} finally {
			try {
				socket.close();
			} catch(IOException e) {
				System.out.println("5");
			}
		}
	}*/
	private Socket socket;
	private BufferedReader input2;
	private PrintWriter output2;
	private Player other;
	private DataInputStream in; // 기초 자료형 읽기
	private DataOutputStream out; 
	private BufferedReader input;
	private PrintWriter output;

	public Player(Socket socket) {
		this.socket=socket;
		try {
			input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output=new PrintWriter(socket.getOutputStream(),true);
			//in=new DataInputStream(socket.getInputStream()); 
			//out=new DataOutputStream(socket.getOutputStream()); 
			//output= new ObjectOutputStream(socket.getOutputStream());
			//input=new ObjectInputStream(socket.getInputStream());

		} catch(IOException e) {
			System.out.println("연결 끊어짐"+e);
		}
	}
	public void setOther(Player other) {
		this.other=other;	
	}
	public void run() {
		String command;
		try {
			while ((command = input.readLine()) != null) { 
				//command=input.readLine();
				if(command.startsWith("SET"))
						other.output.println(command);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("서버의 run에서 오류");
		}


		// int otherLength=in.readInt(); 
		// other.out.writeInt(otherLength); 


		System.out.println("연결 끊어짐");
		try {
			socket.close();
		}catch(IOException e) {
			System.out.println("소켓 못닫음");
		}
	}
}


