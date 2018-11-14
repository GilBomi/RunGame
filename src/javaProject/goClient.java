package javaProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class goClient extends JFrame{
	/*private Socket socket;
	private ObjectInputStream input; // 바이트 읽어오기
	private ObjectOutputStream output; // 사진 보내기
	private DataInputStream in; // 기초 자료형 읽기
	private DataOutputStream out; 

	private Image myFace,otherFace;
	private JLabel myName,otherName;
	private int length=0,otherLength=0;
	private int speed;*/
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private char me,other;

	class MyThread extends Thread {
		public void run() {
			/*try {
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
				}*/
			String response;
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//public goClient(Image characterFace,String goName) throws UnknownHostException,IOException {
	public goClient() throws UnknownHostException, IOException {	
		/*socket=new Socket("localhost",9001);

		input=new ObjectInputStream(socket.getInputStream());
		output=new ObjectOutputStream (socket.getOutputStream());
		myFace=characterFace;
		this.myName=new JLabel(goName);
		this.myName.setSize(200,200); // 레이블 패널이나 프레임에 더해야되는데 어떻게할까

		output.writeObject(characterFace);
		out.writeUTF(goName); // 이름 보내기

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
				try {
					out.writeInt(length);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // 움직인 총 거리 보내기
				repaint();
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {}
		});
		this.requestFocus();
		setFocusable(true);*/
		setUndecorated(true);
		socket=new Socket("localhost",9003);
		input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output=new PrintWriter(socket.getOutputStream(),true);
		
		(new MyThread()).start();
	}

	/*public void run() {

	}*/
}
