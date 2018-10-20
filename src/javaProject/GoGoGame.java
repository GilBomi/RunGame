package javaProject;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



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

	private boolean characterPage=false;
	private Image characterSelect=new ImageIcon(Main.class.getResource("../image/chimmy.png")).getImage();
	private Image nameSelect=new ImageIcon(Main.class.getResource("../image/chimmyName.png")).getImage();

	private ImageIcon charSelectRightButtonImage = new ImageIcon(Main.class.getResource("../image/rightButton.png"));
	private ImageIcon charSelectLefttButtonImage=new ImageIcon(Main.class.getResource("../image/leftButton.png"));
	private JButton charSelectRightButton = new JButton(charSelectRightButtonImage);
	private JButton charSelectLeftButton = new JButton(charSelectLefttButtonImage);
	private ImageIcon charSelectRightButtonPressed=new ImageIcon(Main.class.getResource("../image/rightButtonPressed.png"));
	private ImageIcon charSelectLeftButtonPressed=new ImageIcon(Main.class.getResource("../image/leftButtonPressed.png"));
	private int mouseX, mouseY;
	private int charSelectNum;

	public GoGoGame() {
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

			};
		});
		add(startButton);

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
			}
		});
		add(descriptionButton);

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
			break;
		case 1: 
			characterSelect=new ImageIcon(Main.class.getResource("../image/RJ.png")).getImage();
			nameSelect=new ImageIcon(Main.class.getResource("../image/rjName.png")).getImage();
			break;
		case 2:
			characterSelect=new ImageIcon(Main.class.getResource("../image/Koya.png")).getImage();
			nameSelect=new ImageIcon(Main.class.getResource("../image/koyaName.png")).getImage();
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
}
