package javaProject;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;

public class goClient extends GoGoGame {
	private Socket socket;
	private ObjectInputStream input; // 바이트 읽어오기
	private ObjectOutputStream output; // 사진 보내기
	private DataInputStream in; // 기초 자료형 읽기
	private DataOutputStream out; 

	private Image myFace,otherFace;
	private JLabel myName,otherName;
	private int length=0,otherLength=0;
	private int speed;

	class MyThread extends Thread {
		public void run() {
			try {
				otherFace=(Image) input.readObject();
				otherName=new JLabel(in.readUTF());
				otherName.setSize(200,500);
				while(true) {
					int otherLength=in.readInt();
					otherName.setSize(200+otherLength,500);
					try {
						Thread.sleep(200);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch(IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try  {
					socket.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public goClient(Image characterFace,String goName) throws UnknownHostException,IOException {
		socket=new Socket("localhost",9001);

		input=new ObjectInputStream(socket.getInputStream());
		output=new ObjectOutputStream (socket.getOutputStream());
		myFace=characterFace;
		this.myName=new JLabel(goName);
		this.myName.setSize(200,200); // 레이블 패널이나 프레임에 더해야되는데 어떻게할까


		output.writeObject(characterFace);

		addKeyListener(new KeyListener() { // 패널에 추가해 프레임에 추가해?
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==' ') {
					speed=10;
					length+=speed;
				}
				else 
					speed=0;
				myName.setSize(100+length,100); // Face변수도 옮겨줘야함
				repaint();
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {}
		});
		this.requestFocus();
		setFocusable(true);
		
		output.writeObject(myFace); // 이미지 보내기
		out.writeUTF(goName); // 이름 보내기
		out.writeInt(length); // 움직인 총 거리 보내기
		(new MyThread()).start();
	}

	/*public void run() {

	}*/
}
