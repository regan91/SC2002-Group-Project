package User;

import java.util.ArrayList;

// Function: UserList class to implement campApp

public class UserList {
    private ArrayList<Student> studentList;
    private ArrayList<Staff> staffList;

    public UserList() {
        studentList = new ArrayList<>();
        staffList = new ArrayList<>();
    }

    public void addStudent(Student newStudent) {
        studentList.add(newStudent);
    }

    public void addStaff(Staff newStaff) {
        staffList.add(newStaff);
    }

    public Student getStudent(int studentIndex) {
        return studentList.get(studentIndex);
    }

    public Staff getStaff(int staffIndex) {
        return staffList.get(staffIndex);
    }
}
