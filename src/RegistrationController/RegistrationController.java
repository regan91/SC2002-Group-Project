package RegistrationController;

import RegistrationController.Interfaces.IRegistrationController;

import java.util.ArrayList;

public class RegistrationController implements IRegistrationController {
    ParticipantList participantData = new ParticipantList();

    public ArrayList<Participant> getAttendeeMembers() {
        return participantData.getAttendee();
    }

    public ArrayList<Participant> getCommitteeMembers() {
        return participantData.getCommittee();
    }

    public void registerCamp(boolean isCommittee, String studentID) {
        Participant newPart = new Participant();
        newPart.setID(studentID);
        if (isCommittee){
            participantData.addCommittee(newPart);
        }else{
            participantData.addAttendee(newPart);
        }
    }

    public void withdrawCamp(String studentID) {
        Participant foundPart = participantData.getSpecificAttendee(studentID);
        participantData.dropAttendee(foundPart);
    }
}
