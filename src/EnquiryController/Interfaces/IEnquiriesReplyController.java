package EnquiryController.Interfaces;

public interface IEnquiriesReplyController {
    void submitReply(String studentID, int enquiryID,  String enquiry);
    void editReply(String studentID, int enquiryID);
}
