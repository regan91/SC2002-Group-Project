package EnquiryController;

import EnquiryController.Interfaces.IEnquiriesQuestionController;
import EnquiryController.Interfaces.IEnquiriesReplyController;

import java.util.ArrayList;

import static CAMs.CampApp.campScanner;

public class EnquiriesController implements IEnquiriesReplyController, IEnquiriesQuestionController {
    EnquiresList enquirydata = new EnquiresList();
    public void viewEnquiriesRecords() {
        System.out.println("Finding all Enquiry Records:");
        ArrayList<Enquiry> matchedEnquiry = enquirydata.getEnquiries();
        for (Enquiry enqRecord : matchedEnquiry) {
            System.out.println("-------\n");
            System.out.println("Enquiry ID: " + enqRecord.getEnquiryID());
            System.out.println("Enquiry Question: " + enqRecord.getQuestion());
            System.out.println("Enquiry Asked By: " + enqRecord.getStudentID());
            System.out.println("Enquiry Reply: " + enqRecord.getReply());
            System.out.println("Enquiry Replied By: " + enqRecord.getReplierID());
            System.out.println("-------\n\n");
        }
    }
    public void viewAllEnquiries(String studentID) {
        System.out.println("Finding all question records of Student: " + studentID + "\n");
        ArrayList<Enquiry> matchedEnquiry = enquirydata.getAllEnquries(studentID);
        for (Enquiry enqRecord : matchedEnquiry) {
            System.out.println("-------\n");
            System.out.println("Enquiry ID: " + enqRecord.getEnquiryID());
            System.out.println("Enquiry Question: " + enqRecord.getQuestion());
            System.out.println("Enquiry Asked By: " + enqRecord.getStudentID());
            System.out.println("Enquiry Reply: " + enqRecord.getReply());
            System.out.println("Enquiry Replied By: " + enqRecord.getReplierID());
            System.out.println("-------\n\n");
        }
    }

    public void viewEnquiry(String studentID, int enquiryID) {
        System.out.println("Finding Enquiry " + enquiryID + " of studentID:" + studentID + "\n");
        ArrayList<Enquiry> matchedEnquiry = enquirydata.getEnquries(studentID, enquiryID);
        for (Enquiry enqRecord : matchedEnquiry) {
            System.out.println("-------\n");
            System.out.println("Enquiry ID: " + enqRecord.getEnquiryID());
            System.out.println("Enquiry Question: " + enqRecord.getQuestion());
            System.out.println("Enquiry Asked By: " + enqRecord.getStudentID());
            System.out.println("Enquiry Reply: " + enqRecord.getReply());
            System.out.println("Enquiry Replied By: " + enqRecord.getReplierID());
            System.out.println("-------\n\n");
        }
    }

    public void viewEnquiryByID(int enquiryID) {
        ArrayList<Enquiry> matchedEnquiry = enquirydata.getEnquriesByID(enquiryID);
        for (Enquiry enqRecord : matchedEnquiry) {
            System.out.println("Finding Enquiry " + enquiryID + " of studentID:" + enqRecord.getStudentID() + "\n");
            System.out.println("-------\n");
            System.out.println("Enquiry ID: " + enqRecord.getEnquiryID());
            System.out.println("Enquiry Question: " + enqRecord.getQuestion());
            System.out.println("Enquiry Asked By: " + enqRecord.getStudentID());
            System.out.println("Enquiry Reply: " + enqRecord.getReply());
            System.out.println("Enquiry Replied By: " + enqRecord.getReplierID());
            System.out.println("-------\n\n");
        }
    }

    public void submitEnquiry(String studentID,  String enquiry) {

        Enquiry newEnq = new Enquiry();
        newEnq.setQuestion(enquiry);
        newEnq.setStudentID(studentID);
        System.out.println("Submitting query of studentID: " + studentID + "\n");
        enquirydata.addEnqury(newEnq);
    }

    public void editEnquiry(String studentID, int enquiryID) {
        System.out.println("Editing Enquiry " + enquiryID + " question of studentID: " + studentID + "\n");
        System.out.println();
        System.out.println("Please enter question: ");
        String newQuestion = campScanner.nextLine();
        ArrayList<Enquiry> matchedEnquiry = enquirydata.getEnquries(studentID, enquiryID);
        for (Enquiry enqRecord : matchedEnquiry) {
            enqRecord.setQuestion(newQuestion);
            System.out.println("-------\n");
            System.out.println("Enquiry ID: " + enqRecord.getEnquiryID());
            System.out.println("Enquiry Question: " + enqRecord.getQuestion());
            System.out.println("Enquiry Asked By: " + enqRecord.getStudentID());
            System.out.println("Enquiry Reply: " + enqRecord.getReply());
            System.out.println("Enquiry Replied By: " + enqRecord.getReplierID());
            System.out.println("-------\n\n");
        }
    }

    public void deleteEnquiry(String studentID, int enquiryID) {
        System.out.println("Deleting enquiry " + enquiryID + " of student: " + studentID + "\n");
        ArrayList<Enquiry> matchedEnquiry = enquirydata.getEnquries(studentID, enquiryID);
        for (Enquiry enqRecord : matchedEnquiry) {
            enquirydata.dropEnquiry(enqRecord);
        }
    }

    public void viewAllReply(String studentID) {
        System.out.println("Finding all reply records of Student: " + studentID + "\n");
        ArrayList<Enquiry> matchedEnquiry = enquirydata.getAllEnquries(studentID);
        for (Enquiry enqRecord : matchedEnquiry) {
            System.out.println("-------\n");
            System.out.println("Enquiry ID: " + enqRecord.getEnquiryID());
            System.out.println("Enquiry Question: " + enqRecord.getQuestion());
            System.out.println("Enquiry Asked By: " + enqRecord.getStudentID());
            System.out.println("Enquiry Reply: " + enqRecord.getReply());
            System.out.println("Enquiry Replied By: " + enqRecord.getReplierID());
            System.out.println("-------\n\n");
        }
    }

    public void viewReply(String studentID, int enquiryID) {
        System.out.println("Finding Enquiry " + enquiryID + " of studentID: " + studentID + "\n");
        ArrayList<Enquiry> matchedEnquiry = enquirydata.getEnquries(studentID, enquiryID);
        for (Enquiry enqRecord : matchedEnquiry) {
            System.out.println("-------\n");
            System.out.println("Enquiry ID: " + enqRecord.getEnquiryID());
            System.out.println("Enquiry Question: " + enqRecord.getQuestion());
            System.out.println("Enquiry Asked By: " + enqRecord.getStudentID());
            System.out.println("Enquiry Reply: " + enqRecord.getReply());
            System.out.println("Enquiry Replied By: " + enqRecord.getReplierID());
            System.out.println("-------\n\n");
        }
    }

    public void submitReply(String studentID, int enquiryID, String enquiry) {
        ArrayList<Enquiry> matchedEnquiry = enquirydata.getEnquriesByID(enquiryID);
        if (matchedEnquiry.size() == 0){
            System.out.println("Error: Invalid enquiry ID");
            return;
        }
        for (Enquiry enqRecord : matchedEnquiry) {
            enqRecord.setReply(enquiry);
            enqRecord.setReplierID(studentID);
            System.out.println("-------\n");
            System.out.println("Enquiry ID: " + enqRecord.getEnquiryID());
            System.out.println("Enquiry Question: " + enqRecord.getQuestion());
            System.out.println("Enquiry Asked By: " + enqRecord.getStudentID());
            System.out.println("Enquiry Reply: " + enqRecord.getReply());
            System.out.println("Enquiry Replied By: " + enqRecord.getReplierID());
            System.out.println("-------\n\n");
        }
    }

    public void editReply(String studentID, int enquiryID) {
        System.out.println("Editing Enquiry " + enquiryID + " reply of studentID: " + studentID + "\n");
        System.out.println();
        System.out.println("Please enter the new reply: ");
        String newQuestion = campScanner.nextLine();
        ArrayList<Enquiry> matchedEnquiry = enquirydata.getEnquries(studentID, enquiryID);
        if (matchedEnquiry.size() == 0){
            System.out.println("Error: Invalid enquiry ID");
            return;
        }
        for (Enquiry enqRecord : matchedEnquiry) {
            enqRecord.setQuestion(newQuestion);
            System.out.println("-------\n");
            System.out.println("Enquiry ID: " + enqRecord.getEnquiryID());
            System.out.println("Enquiry Question: " + enqRecord.getQuestion());
            System.out.println("Enquiry Asked By: " + enqRecord.getStudentID());
            System.out.println("Enquiry Reply: " + enqRecord.getReply());
            System.out.println("Enquiry Replied By: " + enqRecord.getReplierID());
            System.out.println("-------\n\n");
        }

    }

    public void deleteReply(String studentID, int enquiryID) {
        System.out.println("Deleting reply " + enquiryID + " of student: " + studentID + "\n");
        ArrayList<Enquiry> matchedEnquiry = enquirydata.getEnquries(studentID, enquiryID);
        for (Enquiry enqRecord : matchedEnquiry) {
            enquirydata.dropEnquiry(enqRecord);
        }
    }
}
