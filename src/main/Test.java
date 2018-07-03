package main;

import java.awt.image.BufferedImage;

import tcp.ConnectionClient;
import tcp.callback.RequestCallback;
import tcp.protocol.BasicProtocol;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ScreenShoter ss=new ScreenShoter();
//		BufferedImage bi=ss.getScreen();
//		ss.img2file(bi, "jpg", "/home/usiel/Pictures/img"+System.currentTimeMillis());
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

}
