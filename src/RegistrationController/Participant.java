package RegistrationController;

import RegistrationController.Interfaces.IParticipant;

public class Participant implements IParticipant {
    private String studentID;
    public String getID() {
        return this.studentID;
    }

    public void setID(String studentID){
        this.studentID = studentID;
    }
}
