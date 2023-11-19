package SuggestionController;

import EnquiryController.Enquiry;
import SuggestionController.Interfaces.ISuggestionController;

import java.util.ArrayList;

import static CAMs.CampApp.campScanner;

public class SuggestionController implements ISuggestionController {
    SuggestionList suggestionData = new SuggestionList();
    public void submitSuggestion(String studentID, String suggestion) {
        Suggestion newSugg = new Suggestion();
        newSugg.setSuggestion(suggestion);
        newSugg.setStudentID(studentID);
        suggestionData.addSuggestion(newSugg);
    }

    public void viewAllSuggestionsRecords() {
        System.out.println("Finding all Enquiry Records:");
        ArrayList<Suggestion> matchedEnquiry = suggestionData.getSuggestions();
        for (Suggestion suggRecord : matchedEnquiry) {
            System.out.println("-------");
            System.out.println("Suggestion ID: " + suggRecord.getSuggestionID());
            System.out.println("Suggestion: " + suggRecord.getSuggestion());
            System.out.println("Suggestion Approval: " + suggRecord.getApproval());
            System.out.println("Suggestion By: " + suggRecord.getStudentID());
            System.out.println("-------\n\n");
        }
    }
    public void viewSuggestions(String studentID) {
        System.out.println();
        System.out.println("Finding all suggestion records of Student: " + studentID + "\n");
        ArrayList<Suggestion> matchedSuggestion = suggestionData.getAllSuggestions(studentID);
        for (Suggestion suggRecord : matchedSuggestion) {
            System.out.println("-------");
            System.out.println("Suggestion ID: " + suggRecord.getSuggestionID());
            System.out.println("Suggestion contents : " + suggRecord.getSuggestion());
            System.out.println("Suggestion Approval: " + suggRecord.getApproval());
            System.out.println("-------\n\n");
        }
    }

    public void viewSpecificSuggestion(String studentID, int suggestionID) {
        System.out.println();
        System.out.println("Finding all suggestion records of Student: " + studentID + "\n");
        ArrayList<Suggestion> matchedSuggestion = suggestionData.getSuggestion(studentID, suggestionID);
        for (Suggestion suggRecord : matchedSuggestion) {
            System.out.println("-------");
            System.out.println("Suggestion ID: " + suggRecord.getSuggestionID());
            System.out.println("Suggestion contents : " + suggRecord.getSuggestion());
            System.out.println("Suggestion Approval: " + suggRecord.getApproval());
            System.out.println("-------\n\n");
        }

    }

    public void editSuggestions(String studentID, int suggestionID) {
        System.out.println("Editing suggestion " + suggestionID + " records of Student: " + studentID + "\n");
        System.out.println();
        System.out.println("Please enter new suggestion: ");
        String newSuggestion = campScanner.nextLine();
        ArrayList<Suggestion> matchedSuggestion = suggestionData.getSuggestion(studentID, suggestionID);
        for (Suggestion suggRecord : matchedSuggestion) {
            suggRecord.setSuggestion(newSuggestion);
            System.out.println("-------");
            System.out.println("Suggestion ID: " + suggRecord.getSuggestionID());
            System.out.println("Suggestion contents : " + suggRecord.getSuggestion());
            System.out.println("Suggestion Approval: " + suggRecord.getApproval());
            System.out.println("-------\n\n");
        }

    }

    public void deleteSuggestion(String studentID, int suggestionID) {
        System.out.println("Deleting suggestion " + suggestionID + " of student: " + studentID + "\n");
        ArrayList<Suggestion> matchedEnquiry = suggestionData.getSuggestion(studentID, suggestionID);
        for (Suggestion suggRecord : matchedEnquiry) {
            suggestionData.dropSuggestion(suggRecord);
        }

    }

    public void approveSuggestion(int suggestionID) {
        ArrayList<Suggestion> matchedEnquiry = suggestionData.getSuggestionByID(suggestionID);
        for (Suggestion suggRecord : matchedEnquiry) {
            System.out.println("Approving suggestion" + suggestionID + " of student: " + suggRecord.getStudentID() + "\n");
            suggestionData.dropSuggestion(suggRecord);
            suggestionData.approveSuggestion(suggRecord);
        }

    }
}
