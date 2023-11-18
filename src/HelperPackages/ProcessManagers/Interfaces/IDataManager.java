package HelperPackages.ProcessManagers.Interfaces;

import UserTypes.Staff;
import UserTypes.Student;
import UserTypes.User;

import java.util.ArrayList;
import java.util.List;

public interface IDataManager {
    List<String> reader(String filePath);
    void writer(String filePath, List<String> storeData);

    User formatter(String recordData, boolean isStaffMember);

    ArrayList<String> generateStaffRecords(ArrayList<Staff> targetList);
    ArrayList<String> generateStudentRecords(ArrayList<Student> targetList);
}
