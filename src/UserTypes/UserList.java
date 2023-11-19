package UserTypes;

import UserTypes.Interfaces.IUserList;

import java.util.ArrayList;

public class UserList implements IUserList {

    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Staff> staffList = new ArrayList<>();
    public void addStudent(Student newStudent) {
        studentList.add(newStudent);
    }

    public void addStaff(Staff newStaff) {
        staffList.add(newStaff);
    }

    public void upgradeStudent (Student oldStudent, CommitteeMember newMember){
        this.studentList.add(newMember);
        this.studentList.remove(oldStudent);
    }

    public ArrayList<Student> getAllStudent(){return  this.studentList;}

    public ArrayList<Staff> getAllStaff(){
        return this.staffList;
    }
    public Student getStudent(String studentID) {
        for (Student listRecord : studentList) {
            if (listRecord.getUserID().contains(studentID)){
                return listRecord;
            }
        }
        return null;
    }

    public Staff getStaff(String staffID) {
        for (Staff listRecord : staffList) {
            if (listRecord.getUserID().contains(staffID)){
                return listRecord;
            }
        }
        return null;
    }
}
