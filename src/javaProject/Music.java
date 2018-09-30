package javaProject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javaProject.Main;
import javazoom.jl.player.Player;

public class Music extends Thread{
	private Player player;
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
		this.interrupt();
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
