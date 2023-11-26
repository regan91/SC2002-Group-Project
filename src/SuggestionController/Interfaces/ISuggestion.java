package SuggestionController.Interfaces;

public interface ISuggestion {
    int getSuggestionID();
    String getStudentID();
    void setStudentID(String studentID);
    String getSuggestion();
    void setSuggestion(String suggestion);
    boolean getApproval();
    void setApproval(boolean isApproved);
}
