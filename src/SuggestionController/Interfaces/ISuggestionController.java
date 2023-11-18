package SuggestionController.Interfaces;

public interface ISuggestionController {
    void submitSuggestion(String studentID, String suggestion);
    void viewSuggestions(String studentID);
    void editSuggestions(String studentID, int suggestionID);
    void deleteSuggestion(String studentID, int suggestionID);
    void approveSuggestion(String studentID, int suggestionID);
}
