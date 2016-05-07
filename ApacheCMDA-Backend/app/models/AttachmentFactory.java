package models;


public class AttachmentFactory {
    public AttachmentFactory() {

    }

    public Attachment getAttachment(String attachmentType) {
        if(attachmentType == null) {
            return null;
        }
        if(attachmentType.equalsIgnoreCase("reply")) {
            return new Reply();
        } else if(attachmentType.equalsIgnoreCase("suggestion")) {
            return new Suggestions();
        }
        return null;
    }
}