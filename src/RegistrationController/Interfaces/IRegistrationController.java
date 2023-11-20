package RegistrationController.Interfaces;

import RegistrationController.Participant;

import java.util.ArrayList;

public interface IRegistrationController {
    ArrayList<Participant> getAttendeeMembers();
    ArrayList<Participant> getCommitteeMembers();
    boolean registerCamp(boolean isCommittee, String studentID);
    void withdrawCamp(String studentID);
}
