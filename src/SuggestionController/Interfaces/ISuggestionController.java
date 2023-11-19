package SuggestionController.Interfaces;

import UserTypes.CommitteeMember;

public interface ISuggestionController {
    String getStudentID(int suggestionID);
    void submitSuggestion(String studentID, String suggestion);
    void viewSuggestions(String studentID);
    void viewSpecificSuggestion(String studentID, int suggestionID);
    void editSuggestions(String studentID, int suggestionID);
    void deleteSuggestion(String studentID, int suggestionID);
    void approveSuggestion(CommitteeMember student, int suggestionID);
}
