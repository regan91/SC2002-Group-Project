package User;

import java.util.ArrayList;

// subclass of User

public class Student extends User {
    private ArrayList<Integer> registeredCamps;

    public Student(String userId, String password, String faculty) {
        super(userId, password);
        this.setUserType(false);
        this.setFaculty(faculty);
        this.registeredCamps = new ArrayList<>();
    }

    public ArrayList<Integer> getRegisteredCamps() {
        return registeredCamps;
    }

    public void setRegisteredCamps(int campId) {
        registeredCamps.add(campId);
    }
}
