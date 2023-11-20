package SuggestionController;

import SuggestionController.Interfaces.ISuggestion;

public class Suggestion implements ISuggestion {
    private final int suggestionID = (int) (Math.random() * 1000);
    private String studentID;
    private String suggestion;
    private boolean approval = false;

    public int getSuggestionID(){
        return this.suggestionID;
    }
    public String getStudentID() {
        return this.studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSuggestion() {
        return this.suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public boolean getApproval() {
        return this.approval;
    }

    public void setApproval(boolean isApproved) {
        this.approval = isApproved;
    }
}
