package EnquiryController.Interfaces;

public interface IEnquiriesQuestionController {
    void viewAllEnquiries(String studentID);
    void viewEnquiry(String studentID,int enquiryID);
    void submitEnquiry(String studentID, String enquiry);
    void editEnquiry(String studentID, int enquiryID, String newEnquiry);

    void deleteEnquiry(String studentID, int enquiry);
}
