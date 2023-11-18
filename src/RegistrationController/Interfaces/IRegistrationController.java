package RegistrationController.Interfaces;

public interface IRegistrationController {
    void registerCamp(boolean isCommittee, String studentID);
    void withdrawCamp(String studentID);
}
