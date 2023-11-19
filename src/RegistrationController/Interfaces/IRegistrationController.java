package RegistrationController.Interfaces;

import RegistrationController.Participant;

import java.util.ArrayList;

public interface IRegistrationController {
    ArrayList<Participant> getAttendeeMembers();
    ArrayList<Participant> getBlockedMembers();
    boolean isBlockedMember(String studentID);
    ArrayList<Participant> getCommitteeMembers();
    void registerCamp(boolean isCommittee, String studentID);
    void withdrawCamp(String studentID);
}
