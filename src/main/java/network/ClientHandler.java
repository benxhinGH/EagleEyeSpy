package network;

import entity.ImageCacheManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.*;
import utils.*;

import java.io.File;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    private final String TAG = ClientHandler.class.getSimpleName();

    private ClientCallback callback;
    private ExecutorService executor;

    public ClientHandler(ClientCallback callback){
        this.callback = callback;
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(ProtocolFactory.createIdentificationRequest());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BasicProtocol basicProtocol = (BasicProtocol)msg;
        int transactionId = basicProtocol.getTransactionId();
        Log.log(TAG, LogLevel.INFO, "receive msg:" + basicProtocol.toString());
        switch (basicProtocol.getMsgId()){
            case MsgId.SCREENSHOT_REQUEST:
                BasicProtocol screenShotResponse = ProtocolFactory.createScreenShotResponse(transactionId);
                ctx.writeAndFlush(screenShotResponse);
                File imageFile = ImageCacheManager.getInstance().newImageFile(transactionId);
                ScreenShoter.img2file(ScreenShoter.getScreen(), Config.DEFAULT_IMAGE_FORMAT, imageFile);
                BasicProtocol fileRequest = ProtocolFactory.createFileSendRequest(transactionId, imageFile);
                ctx.writeAndFlush(fileRequest);
                break;
            case MsgId.FILE_SEND_RESPONSE:
                if(ErrorCode.SUCCESS == basicProtocol.getErrorCode()){
                    int trgPort = Util.byteArrayToInt(basicProtocol.getDataArray());
                    File file = ImageCacheManager.getInstance().getCache(transactionId);
                    if(file == null){
                        Log.e(TAG, "file should not be null");
                    }
                    InetAddress remoteAddr = Util.getChannelRemoteAddressIp(ctx);
                    FileSender fileSender = new FileSender(remoteAddr, trgPort, file, new FileSenderCallback() {
                        @Override
                        public void currentProgress(int progress) {

                        }

                        @Override
                        public void finish() {
                            ImageCacheManager.getInstance().free(transactionId);
                        }
                    });
                    executor.execute(fileSender);
                    Log.i(TAG, "send file:" + file.getName() + " to:" + remoteAddr.getHostAddress() + ":" + trgPort);
                }
                break;
            case MsgId.IDENTIFICATION_RESPONSE:
                if(ErrorCode.SUCCESS != basicProtocol.getErrorCode()){
                    Log.e(TAG, "identification fail!");
                    ctx.close();
                }
                break;
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
