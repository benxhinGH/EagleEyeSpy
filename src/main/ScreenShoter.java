package main;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScreenShoter {
	
	public BufferedImage getScreen() {
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
	
	public void img2file(BufferedImage bi,String suffix,String newFile) {
		try {
			ImageIO.write(bi, suffix, new File(newFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
