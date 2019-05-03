package tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileSendTask {

	private File file;
	
	public FileSendTask(File file) {
		this.file=file;
	}
	
	
	public void send() {
		try {
			Socket socket=new Socket(InetAddress.getByName(Config.ADDRESS),Config.FILE_PORT);
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos=new BufferedOutputStream(socket.getOutputStream());
			byte[] buf=new byte[4096];
			int r=0;
			while((r=bis.read(buf))!=-1) {
				bos.write(buf,0,r);
			}
			bos.flush();
			bos.close();
			bis.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
