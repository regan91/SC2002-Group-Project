package UserTypes;

import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Integer> registeredCamp = new ArrayList<>();
    private boolean isCommittee = false;
    private int commiteeCamp = -1;

    public boolean getIsCommittee(){
        return this.isCommittee;
    }

    public int getCommitteeCamp(){
        return this.commiteeCamp;
    }

    public void setIsCommittee(int campID ){
        this.isCommittee = true;
        this.commiteeCamp = campID;
    }
    public void addRegisteredCamp(int campID){
        registeredCamp.add(campID);
    }
    public void removeRegisteredCamp(int campID){
        int index = 0;
        for (int element: registeredCamp){
            index += 1;
            if (element == campID){
                break;
            }
        }
        registeredCamp.remove(index);
    }
    public ArrayList<Integer> getRegisteredCamps(){
        return registeredCamp;
    }
}
