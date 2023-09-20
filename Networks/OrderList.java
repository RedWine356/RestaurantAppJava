package Networks;

import data.AllFood;
import java.io.Serializable;

public class OrderList implements Serializable{
    private String ResName;
    private MessageHeader messageHeader;
    private AllFood f;

    public OrderList(String resName, MessageHeader messageHeader, AllFood f) {
        ResName = resName;
        this.messageHeader = messageHeader;
        this.f = f;
    }

    public String getResName() {
        return ResName;
    }

    public void setResName(String resName) {
        ResName = resName;
    }

    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public AllFood getF() {
        return f;
    }

    public void setF(AllFood f) {
        this.f = f;
    }
}
