package util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Util {

	public static byte[] img2byteArray(BufferedImage img) {
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		try {
			ImageIO.write(img, ".png", out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out.toByteArray();
	}
	
}
