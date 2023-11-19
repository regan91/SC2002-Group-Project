package RegistrationController.Interfaces;

import RegistrationController.Participant;

import java.util.ArrayList;

public interface IParticpantList {
    ArrayList<Participant> getAllParticipants();
    ArrayList<Participant> getAttendee();
    Participant getSpecificAttendee(String studentID);
    ArrayList<Participant> getCommittee();
    Participant getSpecificCommittee(String studentID);
    boolean addAttendee(Participant newParticipant);
    boolean addCommittee(Participant newParticipant);
    void dropAttendee(Participant participant);

}
