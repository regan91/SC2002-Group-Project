package CAMs.Interfaces;

import EnquiryController.EnquiriesController;
import RegistrationController.RegistrationController;
import SuggestionController.SuggestionController;
import UserTypes.Staff;

public interface IStaticCampDetails {
    public int getCampID();
    public Staff getStaffIC();

    public EnquiriesController getEnquiries();
    public RegistrationController getRegistrations();
    public SuggestionController getSuggestions();
}
