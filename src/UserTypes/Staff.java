package UserTypes;

import java.util.ArrayList;

public class Staff extends User{
    private ArrayList<Integer> createdCampIDs = new ArrayList<>();

    public ArrayList<Integer> getCreatedCamps(){
        return createdCampIDs;
    }

    public void addNewCamp(int campID){
        createdCampIDs.add(campID);
    }
}
