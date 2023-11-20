package CAMs;

import CAMs.Camp;
import CAMs.Interfaces.ICampListController;

import java.util.ArrayList;

public class CampListController implements ICampListController {
    private CampList campData = new CampList();

    public CampList getList(){
        return campData;
    }
    public ArrayList<Camp> getAllCamps() {
        return campData.getCamps();
    }

    public ArrayList<Camp> getAllCamps(String faculty) {

        return campData.getCamps(faculty);
    }

    public ArrayList<Camp> getCamp(int campID) {
        return campData.getCamps(campID);
    }

    public ArrayList<Camp> getCreatedCamps(String staffID) {
        ArrayList<Camp> createdCamps = new ArrayList<>();
        for (Camp campRecord : campData.getCamps()) {
            if (campRecord.getStaticDetails().getStaffIC().getUserID() == staffID){
                createdCamps.add(campRecord);
            }
        }
        return createdCamps;
    }

}
