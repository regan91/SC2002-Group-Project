package CAMs;

import CAMs.Interfaces.IStaticCampDetails;
import EnquiryController.EnquiriesController;
import RegistrationController.RegistrationController;
import SuggestionController.SuggestionController;
import UserTypes.Staff;

public class StaticCampDetails implements IStaticCampDetails {
    private int campID;
    private Staff staffInCharge;
    private EnquiriesController getEnquiriesTracker;
    private RegistrationController getRegistrationTracker;
    private SuggestionController getSuggestionTracker;

    StaticCampDetails(Staff creatorStaff, SuggestionController sc, EnquiriesController ec, RegistrationController rc){
        this.campID = (int) (Math.random() * 1000);
        this.staffInCharge = creatorStaff;
        this.getEnquiriesTracker = ec;
        this.getSuggestionTracker = sc;
        this.getRegistrationTracker = rc;

    }

    public int getCampID() {
        return this.campID;
    }

    public Staff getStaffIC() {
        return this.staffInCharge;
    }

    public EnquiriesController getEnquiries() {
        return this.getEnquiriesTracker;
    }

    public RegistrationController getRegistrations() {
        return this.getRegistrationTracker;
    }

    public SuggestionController getSuggestions() {
        return this.getSuggestionTracker;
    }
}
