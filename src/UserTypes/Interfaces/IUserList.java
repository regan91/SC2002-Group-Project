package UserTypes.Interfaces;

import UserTypes.Staff;
import UserTypes.Student;

import java.util.ArrayList;

public interface IUserList {

    public ArrayList<Student> getAllStudent();
    public ArrayList<Staff> getAllStaff();
    public void addStudent(Student newStudent);
    public void addStaff(Staff newStaff);
    public Student getStudent(String userID);
    public Staff getStaff(String userID);
}
