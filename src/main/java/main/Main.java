package main;

import java.awt.image.BufferedImage;
import java.io.File;

import tcp.ConnectionClient;
import tcp.FileSendTask;
import tcp.callback.RequestCallback;
import tcp.protocol.BasicProtocol;
import tcp.protocol.DataProtocol;

public class Main {
	
	public static final int CONNECT_PERIOD=5000;
	
	private static ConnectionClient client;
	
	private static RequestCallback callback=new RequestCallback() {

		@Override
		public void onSuccess(BasicProtocol msg) {
			// TODO Auto-generated method stub
			System.out.println("get msg:"+msg.toString());
			if(msg.getProtocolType()==0) {
				DataProtocol data=(DataProtocol)msg;
				if(data.getData().equals("capture")) {
					System.out.println("receive capture command");
					sendFile();
				}
			}
		}

		@Override
		public void onFailed(int errorCode, String msg) {
			// TODO Auto-generated method stub
			System.out.println("errorcode:"+errorCode+" msg:"+msg);
			try {
				Thread.sleep(CONNECT_PERIOD);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectToServer(callback);
		}
		
	};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		connectToServer(callback);

	}
	
	private static void connectToServer(RequestCallback callback) {
		System.out.println("attemp to connect to server");
		client=new ConnectionClient(callback);
	}
	
	public static String takeScreenShot() {
		System.out.println("screen shot");
		ScreenShoter ss=new ScreenShoter();
		BufferedImage bi=ss.getScreen();
		String filePath="/home/usiel/Pictures/img"+System.currentTimeMillis();
		ss.img2file(bi, "png", filePath);
		return filePath;
	}
	
	public static void sendFile() {
		System.out.println("send file");
		File file=new File(takeScreenShot());
		FileSendTask task=new FileSendTask(file);
		task.send();
	}

}
