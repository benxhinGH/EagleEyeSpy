package main;

import java.awt.image.BufferedImage;
import java.io.File;

import utils.ScreenShoter;

public class Main {
	
	public static final int CONNECT_PERIOD=5000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

	
	public static String takeScreenShot() {
		System.out.println("screen shot");
		ScreenShoter ss=new ScreenShoter();
		BufferedImage bi=ss.getScreen();
		String filePath="/home/usiel/Pictures/img"+System.currentTimeMillis();
		ss.img2file(bi, "png", filePath);
		return filePath;
	}


}
