package SuggestionController;

import SuggestionController.Interfaces.ISuggestionList;

import java.util.ArrayList;

public class SuggestionList implements ISuggestionList {
    private ArrayList<Suggestion> suggestions;
    private ArrayList<Suggestion> approvedSuggestions;
    public ArrayList<Suggestion> getAllSuggestions(String studentID) {
        ArrayList<Suggestion> foundSuggestions = new ArrayList<>();
        for (Suggestion target : suggestions) {
            if (target.getStudentID() == studentID) {
                foundSuggestions.add(target);
            }
        }
        return foundSuggestions;
    }

    public ArrayList<Suggestion> getSuggestion(String studentID, int suggestionID) {
        ArrayList<Suggestion> foundSuggestions = new ArrayList<>();
        for (Suggestion target : suggestions) {
            if ((target.getStudentID() == studentID) && (target.getSuggestionID() == suggestionID)) {
                foundSuggestions.add(target);
            }
        }
        return foundSuggestions;
    }

    public void addSuggestion(Suggestion newSuggestion) {
        suggestions.add(newSuggestion);
    }

    public void dropSuggestion(Suggestion oldSuggestion) {
        suggestions.remove(oldSuggestion);
    }
    public void approveSuggestion(Suggestion oldSuggestion) {
        suggestions.remove(oldSuggestion);
        approvedSuggestions.add(oldSuggestion);
    }
}
