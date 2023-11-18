package EnquiryController.Interfaces;

import EnquiryController.Enquiry;

import java.util.ArrayList;

public interface IEnquriesList {
    ArrayList<Enquiry> getEnquiries();
    ArrayList<Enquiry> getAllEnquries(String studentID);
    ArrayList<Enquiry> getEnquries(String studentID, int enquiryID);
    void addEnqury(Enquiry newEnquiry);
    void dropEnquiry(Enquiry enquiry);
}
