package RegistrationController.Interfaces;

import RegistrationController.Participant;

import java.util.ArrayList;

public interface IParticpantList {
    ArrayList<Participant> getAllParticipants();
    ArrayList<Participant> getBlockedParticipants();
    ArrayList<Participant> getAttendee();
    Participant getSpecificAttendee(String studentID);
    ArrayList<Participant> getCommittee();
    Participant getSpecificCommittee(String studentID);
    void addAttendee(Participant newParticipant);
    void addCommittee(Participant newParticipant);
    void dropAttendee(Participant participant);

}
