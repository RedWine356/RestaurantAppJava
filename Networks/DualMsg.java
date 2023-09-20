package Networks;

import java.io.Serializable;
public class DualMsg implements Serializable{
    private MessageHeader messageHeader;
    private String message1;
    private String message2;
    public DualMsg(MessageHeader messageHeader, String message1, String message2) {
        this.messageHeader = messageHeader;
        this.message1 = message1;
        this.message2 = message2;
    }

    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public String getMessage1() {
        return this.message1;
    }
    public String getMessage2() {
        return this.message2;
    }
    public void setMessage1(String message) {
        this.message1 = message;
    }
    public void setMessage2(String message) {
        this.message2 = message;
    }
}
