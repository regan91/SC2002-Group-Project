package SuggestionController.Interfaces;

import SuggestionController.Suggestion;

import java.util.ArrayList;

public interface ISuggestionList {
    ArrayList<Suggestion> getSuggestions();
    ArrayList<Suggestion> getAllSuggestions(String studentID);
    ArrayList<Suggestion> getSuggestion(String studentID, int suggestionID);
    void addSuggestion(Suggestion newSuggestion);
    void dropSuggestion(Suggestion oldSuggestion);
    void approveSuggestion(Suggestion oldSuggestion);
}
