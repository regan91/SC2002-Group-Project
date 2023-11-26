package CAMs;

import CAMs.Interfaces.ICamp;
import EnquiryController.EnquiriesController;
import RegistrationController.RegistrationController;
import SuggestionController.SuggestionController;
import UserTypes.Staff;

public class Camp implements ICamp {


    private StaticCampDetails staticDetails;
    private DynamicCampDetails dynamicDetails = new DynamicCampDetails();
    public StaticCampDetails getStaticDetails() {
        return staticDetails;
    }

    public DynamicCampDetails getDynamicDetails() {
        return dynamicDetails;
    }

    public void setStaticDetails(Staff userStaff) {
        SuggestionController sc = new SuggestionController();
        EnquiriesController ec = new EnquiriesController();
        RegistrationController rc =  new RegistrationController();
        this.staticDetails = new StaticCampDetails(userStaff,sc, ec, rc );
    }
}
