package Networks;

import java.io.Serializable;

public class LoginInfo implements Serializable {
    private MessageHeader messageHeader;
    private String username;
    private String password;
    private String newPassword; // only for requesting password change
    public boolean status;
    public boolean isCus;
    public LoginInfo(MessageHeader messageHeader, String username, String password) {
        this.messageHeader = messageHeader;
        this.username = username;
        this.password = password;
    }

    public LoginInfo(MessageHeader messageHeader, String username, String password, String newPassword) {
        this.messageHeader = messageHeader;
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
    }

    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public void isCus(boolean cus){ this.isCus = cus; }

    public boolean getCus() {
        return isCus;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
