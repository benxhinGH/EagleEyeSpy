package utils;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class ScreenShoter {
	
	public static BufferedImage getScreen() {
		try {
			Robot rb=new Robot();
			Toolkit tk=Toolkit.getDefaultToolkit();
			Dimension di=tk.getScreenSize();
			Rectangle rec=new Rectangle(0,0,(int)di.getWidth(),(int)di.getHeight());
			BufferedImage bi=rb.createScreenCapture(rec);
			return bi;
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void img2file(BufferedImage bi,String suffix,String newFile) {
		try {
			ImageIO.write(bi, suffix, new File(newFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void img2file(BufferedImage bi,String suffix,File newFile) {
		try {
			ImageIO.write(bi, suffix, newFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void img2Stream(BufferedImage bi,String suffix,OutputStream output) {
		try {
			ImageIO.write(bi, suffix, output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
