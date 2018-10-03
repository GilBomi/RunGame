package javaProject;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GoGoGame extends JFrame{
	private Image introBackground;
	private Image screenImage;
	private Graphics screenGraphic;
	
	public GoGoGame() {
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setTitle("Go Go");
		setSize(1080,715);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(screenSize.width/8,screenSize.height/7);
		Image img=kit.getImage("goIcon.jpg");
		//Image img=kit.createImage("../image/goIcon.JPG");
		setIconImage(img);
		introBackground=new ImageIcon(Main.class.getResource("../image/introBackground.jpg")).getImage();
		
		Music introMusic=new Music("ARRIVAL.MP3",true);
		introMusic.start(); // 스레드 실행시작
		setVisible(true);
	}
	public void paint(Graphics g) {
		screenImage=createImage(1080,715); // 이미지 객체 생성
		screenGraphic=screenImage.getGraphics(); // graphics 객체 받기
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null); // 가상화면에 그려진 그림을 실제 화면으로 복사
		this.repaint();
	}

}
