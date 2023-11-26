package CAMs.Interfaces;

import CAMs.Camp;
import CAMs.CampList;

import java.util.ArrayList;

public interface ICampListController {
    CampList getList();
    ArrayList<Camp> getAllCamps();
    ArrayList<Camp> getAllCamps(String faculty);
    ArrayList<Camp> getCamp(int campID);
    ArrayList<Camp> getCreatedCamps (String staffID);

}
