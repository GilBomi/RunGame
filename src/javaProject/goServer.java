package javaProject;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JLabel;

public class goServer {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ServerSocket ss=new ServerSocket(9001);

		try {
			while(true) {
				Player player1=new Player(ss.accept());
				Player player2=new Player(ss.accept());

				player1.setOther(player2);
				player2.setOther(player1);

				player1.start();
				player2.start();
				System.out.println("1");
			}
		} finally {
			ss.close();
		}
	}


}

class Player extends Thread {
	Socket socket;
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
			while(true) {
				other.output.writeObject(myFace);
				other.out.writeUTF(goName);
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
	}
}

