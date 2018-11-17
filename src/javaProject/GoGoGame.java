package javaProject;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class GoGoGame extends JFrame{
	private Image background=new ImageIcon(Main.class.getResource("../image/introBackground.png")).getImage();
	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon startButtonImage=new ImageIcon(Main.class.getResource("../image/startButton.png"));
	private JButton startButton=new JButton(startButtonImage);
	private ImageIcon startButtonPressedImage=new ImageIcon(Main.class.getResource("../image/startButtonPressed.png"));
	private ImageIcon descriptionButtonImage=new ImageIcon(Main.class.getResource("../image/descriptionButton.png"));
	private JButton descriptionButton=new JButton(descriptionButtonImage);
	private ImageIcon descriptionButtonPressedImage=new ImageIcon(Main.class.getResource("../image/descriptionButtonPressed.png"));
	private ImageIcon exitButtonImage1=new ImageIcon(Main.class.getResource("../image/exitButton.png"));
	private JButton exitButton1=new JButton(exitButtonImage1);
	private ImageIcon exitButtonPressedImage1=new ImageIcon(Main.class.getResource("../image/exitButtonPressed.png"));

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../image/menuBar.jpg")));
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../image/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../image/exitButtonBasic.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);

	private ImageIcon backButtonImage = new ImageIcon(Main.class.getResource("../image/backButton.png"));
	private JButton backButton = new JButton(backButtonImage);
	private ImageIcon backButtonPressedImage = new ImageIcon(Main.class.getResource("../image/backButtonPressed.png"));
	private JButton backButtonPressed = new JButton(backButtonPressedImage);

	private boolean characterPage=false;
	private Image characterSelect=new ImageIcon(Main.class.getResource("../image/chimmy.png")).getImage();
	private Image nameSelect=new ImageIcon(Main.class.getResource("../image/chimmyName.png")).getImage();
	private ImageIcon characterFace;
	private JButton characterFaceButton;

	private ImageIcon charSelectRightButtonImage = new ImageIcon(Main.class.getResource("../image/rightButton.png"));
	private ImageIcon charSelectLefttButtonImage=new ImageIcon(Main.class.getResource("../image/leftButton.png"));
	private JButton charSelectRightButton = new JButton(charSelectRightButtonImage);
	private JButton charSelectLeftButton = new JButton(charSelectLefttButtonImage);
	private ImageIcon charSelectRightButtonPressed=new ImageIcon(Main.class.getResource("../image/rightButtonPressed.png"));
	private ImageIcon charSelectLeftButtonPressed=new ImageIcon(Main.class.getResource("../image/leftButtonPressed.png"));
	private int mouseX, mouseY;
	private int charSelectNum;

	private JTextField jtname;
	private String name;

	private Socket socket;
	private BufferedReader input2;
	private PrintWriter output2;
	private DataInputStream in; // 기초 자료형 읽기
	private DataOutputStream out;
	private int speed=0;
	private int length=100;
	private ObjectInputStream input; // 바이트 읽어오기
	private ObjectOutputStream output; // 사진 보내기

	public GoGoGame() throws UnknownHostException,IOException{
		setUndecorated(true); // frame을 없애고
		setBackground(new Color(0, 0, 0,0)); // 배경을 투명으로 설정했기 때문에 그래픽에서 배경이미지와 버튼같은 컴포넌트만 표현됨
		//Toolkit kit=Toolkit.getDefaultToolkit();
		//Dimension screenSize=kit.getScreenSize();
		//setTitle("Go Go");
		setSize(1080,715);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocation(1080/8,715/7);
		// Image img=kit.getImage("goIcon.png");
		//Image img=kit.createImage("../image/goIcon.png");
		//setIconImage(img);

		exitButton.setBounds(1045, 0, 30, 30); // 메뉴바 exit버튼
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("mouseClick.mp3", false);
				buttonEnteredMusic.start();
				System.exit(0);
			}
		});
		add(exitButton);

		menuBar.setBounds(0, 0, 1280, 30); // 메뉴바 
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

		/*이 버튼은 이미지 만들어서 대체하기, 네트워크 보기 위해서 임시로 만듦*/
		JButton test=new JButton("게임 시작");
		test.setBounds(700,500,200,100);
		test.setVisible(false);
		add(test);
		test.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				test.setVisible(false);
				charSelectRightButton.setVisible(false);
				charSelectLeftButton.setVisible(false);
				characterPage=false;

				if(e.getSource()==test || e.getSource()==jtname) {
					name=jtname.getText();
					// jtname은 텍스트 필드
					// name은 문자열
				}
				jtname.setVisible(false);
				/*JLabel t=new JLabel("aaa");
				t.setBounds(100,100,50,50);
				add(t);*/

				// goClient 생성자 메소드 내용
				try {
					socket = new Socket("localhost",9001);
					input=new ObjectInputStream(socket.getInputStream());
					output= new ObjectOutputStream(socket.getOutputStream());
					//input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
					//output=new PrintWriter(socket.getOutputStream(),true);
					// in=new DataInputStream(socket.getInputStream()); 
					// out=new DataOutputStream(socket.getOutputStream()); 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JLabel label=new JLabel(name);
				label.setBounds(100,100,50,50);
				add(label);
				
				characterFaceButton=new JButton(characterFace);
				characterFaceButton.setBounds(100,150,180,180);
				characterFaceButton.setBorderPainted(false);
				characterFaceButton.setContentAreaFilled(false);
				characterFaceButton.setFocusPainted(false);
				add(characterFaceButton);
				try {
					output.writeObject(name);
					output.writeObject(characterFace);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

				addKeyListener(new KeyListener() { 
					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyChar()==' ') {
							speed=10;
						}
						else 
							speed=0;
						length+=speed;
						
						/*try { 
							out.writeInt(300);
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						label.setBounds(300,100,50,50); */
					}
					@Override
					public void keyReleased(KeyEvent e) {}
					@Override
					public void keyTyped(KeyEvent e) {}
				});
				
				(new MyThread()).start();
				//client.start();
			}
		});

		startButton.setBounds(100,200,300,100); // 시작버튼
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonPressedImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic=new Music("mouseClick.mp3",false);
				ButtonEnteredMusic.start();
				descriptionButton.setVisible(false);
				exitButton1.setVisible(false);
				startButton.setVisible(false);
				charSelectRightButton.setVisible(true);
				charSelectLeftButton.setVisible(true);
				background=new ImageIcon(Main.class.getResource("../image/introBackgroundBlur.png")).getImage();
				characterPage=true;
				jtname.setVisible(true);
				/*임시로 만든 버튼, 나중에 이미지로 대체하기*/
				test.setVisible(true);

			};
		});
		add(startButton);

		jtname = new JTextField(10);
		jtname.setVisible(false);
		jtname.setBounds(640, 180, 280, 35);
		add(jtname);

		descriptionButton.setBounds(100,300,300,100); // 설명버튼
		descriptionButton.setBorderPainted(false);
		descriptionButton.setContentAreaFilled(false);
		descriptionButton.setFocusPainted(false);
		descriptionButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				descriptionButton.setIcon(descriptionButtonPressedImage);
				descriptionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				descriptionButton.setIcon(descriptionButtonImage);
				descriptionButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic=new Music("mouseClick.mp3",false);
				ButtonEnteredMusic.start();
				descriptionButton.setVisible(false);
				exitButton1.setVisible(false);
				startButton.setVisible(false);
				background=new ImageIcon(Main.class.getResource("../image/introBackgroundBlur.png")).getImage();
				backButton.setVisible(true);
			}
		});
		add(descriptionButton);

		backButton.setVisible(false);
		backButton.setBounds(50, 70, 100, 100);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonPressedImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic=new Music("mouseClick.mp3",false);
				ButtonEnteredMusic.start();
				background=new ImageIcon(Main.class.getResource("../image/introBackground.png")).getImage();
				startButton.setVisible(true);
				descriptionButton.setVisible(true);
				exitButton1.setVisible(true);
				backButton.setVisible(false);
			}
		});
		add(backButton);

		exitButton1.setBounds(100,400,300,100); // 종료버튼
		exitButton1.setBorderPainted(false);
		exitButton1.setContentAreaFilled(false);
		exitButton1.setFocusPainted(false);
		exitButton1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				exitButton1.setIcon(exitButtonPressedImage1);
				exitButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				exitButton1.setIcon(exitButtonImage1);
				exitButton1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic=new Music("mouseClick.mp3",false);
				ButtonEnteredMusic.start();
				System.exit(0);
			}
		});

		charSelectRightButton.setVisible(false);
		charSelectRightButton.setBounds(380,250,60,60);
		charSelectRightButton.setBorderPainted(false);
		charSelectRightButton.setContentAreaFilled(false);
		charSelectRightButton.setFocusPainted(false);
		charSelectRightButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				charSelectRightButton.setIcon(charSelectRightButtonPressed);
				charSelectRightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				charSelectRightButton.setIcon(charSelectRightButtonImage);
				charSelectRightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic=new Music("mouseClick.mp3",false);
				ButtonEnteredMusic.start();
				charRightSelect();
			}
		});
		add(charSelectRightButton);

		charSelectLeftButton.setVisible(false);
		charSelectLeftButton.setBounds(20,250,60,60);
		charSelectLeftButton.setBorderPainted(false);
		charSelectLeftButton.setContentAreaFilled(false);
		charSelectLeftButton.setFocusPainted(false);
		charSelectLeftButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				charSelectLeftButton.setIcon(charSelectLeftButtonPressed);
				charSelectLeftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				charSelectLeftButton.setIcon(charSelectLefttButtonImage);
				charSelectLeftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic=new Music("mouseClick.mp3",false);
				ButtonEnteredMusic.start();
				charLeftSelect();
			}
		});
		add(charSelectLeftButton);
		add(exitButton1);

		Music introMusic=new Music("ARRIVAL.MP3",true);
		introMusic.start(); // 스레드 실행시작




	}
	public void paint(Graphics g) { // // 이중 버퍼링기법(이미지 전환하면 화면 깜빡임이 없고 부드럽게 하기 위해서)
		screenImage = createImage(1080, 715);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		if(characterPage) {
			g.drawImage(nameSelect, 80,70,null);
			g.drawImage(characterSelect,80,150,null);
		}
		paintComponents(g);
		this.repaint();
	}
	public void charSelect(int charSelectNum) {
		switch(charSelectNum) {
		case 0: 
			characterSelect=new ImageIcon(Main.class.getResource("../image/Chimmy.png")).getImage();
			nameSelect=new ImageIcon(Main.class.getResource("../image/chimmyName.png")).getImage();
			characterFace=new ImageIcon(Main.class.getResource("../image/chimmyFace.png"));
			break;
		case 1: 
			characterSelect=new ImageIcon(Main.class.getResource("../image/RJ.png")).getImage();
			nameSelect=new ImageIcon(Main.class.getResource("../image/rjName.png")).getImage();
			characterFace=new ImageIcon(Main.class.getResource("../image/rjFace.png"));
			break;
		case 2:
			characterSelect=new ImageIcon(Main.class.getResource("../image/Koya.png")).getImage();
			nameSelect=new ImageIcon(Main.class.getResource("../image/koyaName.png")).getImage();
			characterFace=new ImageIcon(Main.class.getResource("../image/koyaFace.png"));
			break;
		}
	}
	public void charRightSelect() {
		if(charSelectNum==2) 
			charSelectNum=0;
		else 
			++charSelectNum;
		charSelect(charSelectNum);

	}
	public void charLeftSelect() {
		if(charSelectNum==0)
			charSelectNum=2;
		else
			--charSelectNum;
		charSelect(charSelectNum);
	}

	class MyThread extends Thread {
		public void run() {
			try {
				String response=(String)input.readObject();
				JLabel other;
				if(response==null)
					System.out.println("널이야");
				if(response!=null) {
					System.out.println("널 아님");
					other=new JLabel(response);
					other.setBounds(100,350,50,50);
					add(other);
				}
				ImageIcon iIcon=(ImageIcon)input.readObject();
				if(iIcon!=null) {
					JButton otherFace=new JButton(iIcon);
					otherFace.setBounds(100,400,180,180);
					otherFace.setBorderPainted(false);
					otherFace.setContentAreaFilled(false);
					otherFace.setFocusPainted(false);
					add(otherFace);
				}
				// int otherLength=in.readInt(); 
				
				/*while(true) {
					otherLength=in.readInt();
					other.setBounds(otherLength,200,50,50);
					try {
						Thread.sleep(200);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}*/
				// add(other);

			} catch (IOException | ClassNotFoundException e11) {
				// TODO Auto-generated catch block
				System.out.println("aaaa");
			}

			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
