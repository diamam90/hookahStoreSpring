package net.company.hookahstore.model;

public class NotificationMessage {
    private String destinationAddress;
    private String destinationName;
    private String subject;
    private String content;

    public NotificationMessage(){}
    public NotificationMessage(String subject,String content){
        this.content=content;
        this.subject=subject;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
