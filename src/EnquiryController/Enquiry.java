package EnquiryController;

import EnquiryController.Interfaces.IEnquiryUserData;

public class Enquiry implements IEnquiryUserData {

    private final int enquiryID = (int) (Math.random() * 1000);
    private String studentID;
    private String question;
    private String replierID;
    private String reply;

    public int getEnquiryID(){
        return enquiryID;
    }
    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReply() {
        return this.reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getReplierID() {
        return this.replierID;
    }

    public void setReplierID(String studentID) {
        this.replierID = studentID;
    }
}
