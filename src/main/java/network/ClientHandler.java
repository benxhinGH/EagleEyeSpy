package network;

import entity.ImageCacheManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.*;
import utils.*;

import java.io.File;
import java.net.InetAddress;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    private final String TAG = ClientHandler.class.getSimpleName();

    private ClientCallback callback;

    public ClientHandler(ClientCallback callback){
        this.callback = callback;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BasicProtocol basicProtocol = (BasicProtocol)msg;
        switch (basicProtocol.getMsgId()){
            case MsgId.SCREENSHOT_REQUEST:
                int taskId = basicProtocol.getTransactionId();
                File imageFile = ImageCacheManager.getInstance().newImageFile();
                ScreenShoter.img2file(ScreenShoter.getScreen(), Config.DEFAULT_IMAGE_FORMAT, imageFile);
                BasicProtocol fileRequest = ProtocolFactory.createFileSendRequest(taskId, imageFile);
                ctx.writeAndFlush(fileRequest);
                break;
            case MsgId.FILE_SEND_RESPONSE:

                default:
                    break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
