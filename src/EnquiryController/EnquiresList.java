package EnquiryController;

import EnquiryController.Interfaces.IEnquriesList;

import java.util.ArrayList;

public class EnquiresList implements IEnquriesList {
    ArrayList<Enquiry> enquiries;
    public ArrayList<Enquiry> getEnquiries() {
        return enquiries;
    }


    public ArrayList<Enquiry> getAllEnquries(String studentID) {
        ArrayList<Enquiry> enquiriesList = new ArrayList<>();
        for (Enquiry enqRecord : enquiries) {
            if (enqRecord.getStudentID() == studentID){
                enquiriesList.add(enqRecord);
            }
        }
        return enquiriesList;
    }

    public ArrayList<Enquiry> getEnquries(String studentID, int enquiryID) {
        ArrayList<Enquiry> enquiriesList = new ArrayList<>();
        for (Enquiry enqRecord : enquiries) {
            if ((enqRecord.getStudentID() == studentID) && enqRecord.getEnquiryID() == enquiryID){
                enquiriesList.add(enqRecord);
            }
        }
        return enquiriesList;
    }

    public void addEnqury(Enquiry newEnquiry) {
        enquiries.add(newEnquiry);
    }

    public void dropEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
    }
}
