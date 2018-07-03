package main;

import java.awt.image.BufferedImage;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScreenShoter ss=new ScreenShoter();
		BufferedImage bi=ss.getScreen();
		ss.img2file(bi, "jpg", "/home/usiel/Pictures/img"+System.currentTimeMillis());

	}

}
