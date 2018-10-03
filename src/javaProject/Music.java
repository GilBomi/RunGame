package javaProject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javaProject.Main;
import javazoom.jl.player.Player;

public class Music extends Thread{
	private Player player; // 게임 프로그램과 별도로 돌아가는 음악 프로그램이므로 동시에 돌아가려면 스레드로 처리
	private boolean isLoop;
	private File file;
	private FileInputStream fls;
	private BufferedInputStream bls;
	
	public Music(String name,boolean isLoop) {
		try {
			this.isLoop=isLoop;
			file=new File(Main.class.getResource("../music/"+name).toURI());
			fls=new FileInputStream(file);
			bls=new BufferedInputStream(fls);
			player=new Player(bls);
		} catch (Exception e) {
			System.out.println("A");
		}
	}
	public void close() {
		isLoop=false;
		player.close();
		this.interrupt(); // 따로 돌아가는 음악 프로그램을 종료해줌
	}
	public void run() {
		try {
			do {
				player.play();
				fls=new FileInputStream(file);
				bls=new BufferedInputStream(fls);
				player=new Player(bls);
			}while (isLoop);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
