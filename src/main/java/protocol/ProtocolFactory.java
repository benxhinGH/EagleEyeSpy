package protocol;

import utils.Config;
import utils.Util;

import java.io.File;
import java.nio.ByteBuffer;

public class ProtocolFactory {

    public static BasicProtocol createFileSendRequest(int taskId, File file){
        FileSendRequest fileSendRequest = new FileSendRequest();
        fileSendRequest.setFileName(file.getName());
        fileSendRequest.setFileLength(file.length());
        BasicProtocol basicProtocol = new BasicProtocol();
        basicProtocol.setMsgId(MsgId.FILE_SEND_REQUEST);
        basicProtocol.setTransactionId((byte) taskId);
        basicProtocol.setDataArray(fileSendRequest.getMsgBytes());
        return basicProtocol;
    }

    public static BasicProtocol createIdentificationRequest(){
        BasicProtocol basicProtocol = new BasicProtocol();
        basicProtocol.setMsgId(MsgId.IDENTIFICATION_REQUEST);
        String localHostname = Util.getLocalHostname();
        IdentificationRequest identificationRequest = new IdentificationRequest(localHostname, IdentificationRequest.TERMINAL_TYPE_SPY);
        basicProtocol.setDataArray(identificationRequest.getBytes());
        return basicProtocol;
    }

    public static BasicProtocol createScreenShotResponse(int transactionId){
        BasicProtocol basicProtocol = new BasicProtocol();
        basicProtocol.setMsgId(MsgId.SCREENSHOT_RESPONSE);
        basicProtocol.setTransactionId((byte)transactionId);
        return basicProtocol;
    }

}
