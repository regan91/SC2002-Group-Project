package UserTypes;

import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Integer> registeredCamp = new ArrayList<>();

    public Student(String name, String userID, String faculty){
        super(name, userID, faculty, false);
    }

    public void addRegisteredCamp(int campID){
        registeredCamp.add(campID);
    }
    public void removeRegisteredCamp(int campID){
        int index = 0;
        for (int element: registeredCamp){
            if (element == campID){
                break;
            }
            index += 1;
        }
        System.out.println(registeredCamp.size());
        System.out.println(index);
        registeredCamp.remove(index);
    }
    public ArrayList<Integer> getRegisteredCamps(){
        return registeredCamp;
    }
}
