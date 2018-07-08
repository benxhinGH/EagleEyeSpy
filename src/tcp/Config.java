package tcp;

/**
 * Created by UsielLau on 2018/4/8 0008 14:52.
 */

public class Config {
	public static final String PIC_BUFFER_DIR="/home/usiel/Pictures/EagleEye/buffer/";
    public static final int VERSION = 1;                 //协议版本号
    public static final String ADDRESS = "127.0.0.1"; //服务器地址
    public static final int PORT = 8880;                 //服务器端口号
    public static final int FILE_PORT=8881;
    public static final String BASE_URL="http://"+ADDRESS+":8080/EagleEyeServer/";
}
