package User;

import java.util.ArrayList;

// subclass of User

public class Staff extends User {
    private ArrayList<Integer> createdCamps;

    public Staff(String userId, String password, String faculty) {
        super(userId, password);
        this.setUserType(true);
        this.setFaculty(faculty);
        this.createdCamps = new ArrayList<>();
    }

    public ArrayList<Integer> getCreatedCamps() {
        return createdCamps;
    }

    public void setCreatedCamps(int campId) {
        createdCamps.add(campId);
    }
}
