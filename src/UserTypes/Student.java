package UserTypes;

import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Integer> registeredCamp = new ArrayList<>();

    public void addRegisteredCamp(int campID){
        registeredCamp.add(campID);
    }
    public ArrayList<Integer> getRegisteredCamps(){
        return registeredCamp;
    }
}
