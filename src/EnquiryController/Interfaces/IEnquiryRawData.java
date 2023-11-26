package EnquiryController.Interfaces;

public interface IEnquiryRawData {
    public int getEnquiryID();
    public String getQuestion();

    public void setQuestion(String question);

    public String getReply();

    public void setReply(String reply);


}
