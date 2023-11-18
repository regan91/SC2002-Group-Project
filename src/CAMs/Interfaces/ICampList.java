package CAMs.Interfaces;

import CAMs.Camp;
import EnquiryController.EnquiriesController;
import RegistrationController.RegistrationController;
import SuggestionController.SuggestionController;
import UserTypes.Staff;

import java.util.ArrayList;

public interface ICampList {
    ArrayList<Camp> getCamps();
    ArrayList<Camp> getCamps(String faculty) ;
    ArrayList<Camp> getCamps(int campID);
    void addCamp(Staff creator, String campName);
    void removeCamp(int campID);
}
