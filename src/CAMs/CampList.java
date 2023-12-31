package CAMs;

import CAMs.Interfaces.ICampList;
import UserTypes.Staff;

import java.util.ArrayList;

public class CampList implements ICampList {
    private final ArrayList<Camp> camps = new ArrayList<>();
    public ArrayList<Camp> getCamps() {
        return camps;
    }
    public ArrayList<Camp> getCamps(String faculty) {
        ArrayList<Camp> visibleCamps = new ArrayList<>();
        for (Camp campRecords: camps){
            if (campRecords.getDynamicDetails().getVisibility() && ((campRecords.getStaticDetails().getStaffIC().getFaculty().equals(faculty)) ||
                         campRecords.getDynamicDetails().getOpenStatus())){
                     visibleCamps.add(campRecords);

            }
        }
        return visibleCamps;
    }

    public ArrayList<Camp> getCamps(int campID) {
        ArrayList<Camp> campList = new ArrayList<>();
        for (Camp campEntry : camps) {
            if (campEntry.getStaticDetails().getCampID() == (campID) )
                campList.add(campEntry);
        }
        return campList;
    }

//     remember to initialize the variables in the staticCampDetails object when creating a new camp!!!

    public void addCamp(Staff staffAcc, String campName) {
        Camp newCamp = new Camp();
        newCamp.setStaticDetails(staffAcc);
        newCamp.getDynamicDetails().setName(campName);
        camps.add(newCamp);
    }

    public void removeCamp(int campID){
        Camp desiredCamp = null;
        for (Camp campRec: camps){
            if (campRec.getStaticDetails().getCampID() == campID){
                desiredCamp = campRec;
            }
        }
        camps.remove(desiredCamp);

    }
}
