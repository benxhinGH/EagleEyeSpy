package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import tcp.Config;
import tcp.ConnectionClient;
import tcp.FileSendTask;
import tcp.callback.RequestCallback;
import tcp.protocol.BasicProtocol;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		ScreenShoter ss=new ScreenShoter();
//		BufferedImage bi=ss.getScreen();
//		ss.img2file(bi, "jpg", "/home/usiel/Pictures/img"+System.currentTimeMillis());
		
		

		
		
		
		//testTcpConnection();
		
		
		testFileSend();

	}
	
	
	public static void testTcpConnection() {
		ConnectionClient client=new ConnectionClient(new RequestCallback() {

			@Override
			public void onSuccess(BasicProtocol msg) {
				// TODO Auto-generated method stub
				System.out.println("onSuccess"+msg.toString());
			}

			@Override
			public void onFailed(int errorCode, String msg) {
				// TODO Auto-generated method stub
				System.out.println("onFailed"+errorCode+"msg"+msg);
			}
			
		});
	}
	
	public static String testScreenShot() {
		ScreenShoter ss=new ScreenShoter();
		BufferedImage bi=ss.getScreen();
		String filePath="/home/usiel/Pictures/img"+System.currentTimeMillis();
		ss.img2file(bi, "png", filePath);
		return filePath;
	}
	
	public static void testFileSend() {
		File file=new File(testScreenShot());
		FileSendTask task=new FileSendTask(file);
		task.send();
	}
	

}
