package com.revature.models;

public class MailHelper {
    private String firstName;
    private String email;

    private String msgType;

    public MailHelper(){

    }

    public MailHelper(String firstName, String email, String msgType) {
        this.firstName = firstName;
        this.email = email;
        this.msgType = msgType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "MailHelper{" +
                "firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", msgType='" + msgType + '\'' +
                '}';
    }
}
