package test;

import java.awt.image.BufferedImage;
import java.io.File;

import utils.ScreenShoter;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		ScreenShoter ss=new ScreenShoter();
//		BufferedImage bi=ss.getScreen();
//		ss.img2file(bi, "jpg", "/home/usiel/Pictures/img"+System.currentTimeMillis());
		
		

		
		
		
		//testTcpConnection();


	}

	
	public static String testScreenShot() {
		ScreenShoter ss=new ScreenShoter();
		BufferedImage bi=ss.getScreen();
		String filePath="/home/usiel/Pictures/img"+System.currentTimeMillis();
		ss.img2file(bi, "png", filePath);
		return filePath;
	}

	

}
