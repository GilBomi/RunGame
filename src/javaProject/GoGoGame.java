package javaProject;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class GoGoGame extends JFrame{
	private Image introBackground=new ImageIcon(Main.class.getResource("../image/introBackground.png")).getImage();
	private Image screenImage;
	private Graphics screenGraphic;
	
	private ImageIcon startButtonImage=new ImageIcon(Main.class.getResource("../image/startButton.png"));
	private JButton startButton=new JButton(startButtonImage);
	
	public GoGoGame() {
		//setUndecorated(true);
		//setBackground(new Color(0, 0, 0, 0));
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setTitle("Go Go");
		setSize(1080,715);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		setLocation(screenSize.width/8,screenSize.height/7);
		// Image img=kit.getImage("goIcon.png");
		Image img=kit.createImage("../image/goIcon.png");
		setIconImage(img);
		introBackground=new ImageIcon(Main.class.getResource("../image/introBackground.png")).getImage();
		
		
		startButton.setBounds(100,180,300,100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic=new Music("mouseClick.mp3",false);
				ButtonEnteredMusic.start();
			}
		});
		add(startButton);
		
		
		Music introMusic=new Music("ARRIVAL.MP3",true);
		introMusic.start(); // 스레드 실행시작
	}
	public void paint(Graphics g) { // // 이중 버퍼링기법(이미지 전환하면 화면 깜빡임이 없고 부드럽게 하기 위해서)
		screenImage = createImage(1080, 715);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
		//screenGraphic.drawImage(background,0,0,null);
		//g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}


}
