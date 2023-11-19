package RegistrationController;

import RegistrationController.Interfaces.IParticpantList;

import java.util.ArrayList;

public class ParticipantList implements IParticpantList {

    private ArrayList<Participant> Attendee = new ArrayList<>();
    private ArrayList<Participant> BlockedAttendees= new ArrayList<>();
    private ArrayList<Participant> Committee= new ArrayList<>();

    public ArrayList<Participant> getAllParticipants() {
        ArrayList<Participant> combinedList = Attendee;
        combinedList.addAll(Committee);
        return combinedList;
    }

    public ArrayList<Participant> getBlockedParticipants() {
        return BlockedAttendees;
    }

    public ArrayList<Participant> getAttendee(){
        return Attendee;
    }
    public Participant getSpecificAttendee(String studentID){
        for (Participant part : Attendee) {
            if (part.getID() == studentID) {
                return part;
            }
        }
        return null;
    }

    public ArrayList<Participant> getCommittee() {
        return Committee;
    }
    public Participant getSpecificCommittee(String studentID) {
        for (Participant part : Committee) {
            if (part.getID() == studentID) {
                return part;
            }
        }
        return null;
    }

    public void addAttendee(Participant newParticipant) {
        Attendee.add(newParticipant);
    }

    public void addCommittee(Participant newParticipant) {
        Committee.add(newParticipant);
    }

    public void dropAttendee(Participant participant) {
        Attendee.remove(participant);
        BlockedAttendees.add(participant);
    }
}
