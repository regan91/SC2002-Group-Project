package EnquiryController.Interfaces;

public interface IEnquiriesReplyController {
    void viewAllReply(String studentID);
    void viewReply(String studentID,int enquiryID);
    void submitReply(String studentID, String enquiry);
    void editReply(String studentID, int enquiryID, String newEnquiry);
    void deleteReply(String studentID, int enquiry);
}
