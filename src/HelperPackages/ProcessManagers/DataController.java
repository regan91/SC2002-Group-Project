package HelperPackages.ProcessManagers;

import HelperPackages.ProcessManagers.Interfaces.IDataController;
import UserTypes.Staff;
import UserTypes.Student;

import java.util.List;

import static CAMs.CampApp.users;

public class DataController implements IDataController {
    private final DataManager dm = new DataManager();
    public void loadStaffData() {
        List<String> StaffData = dm.reader("database/StaffData.csv", "database/StaffBackup.csv");
        for (String staffRecord: StaffData){
            users.addStaff((Staff) dm.formatter(staffRecord, true));
        }
    }

    public void loadStudentData() {
        List<String> StudentData = dm.reader("database/StudentData.csv", "database/StudentBackup.csv");
        for (String studentRecord: StudentData){
            users.addStudent((Student) dm.formatter(studentRecord, false));
        }
    }

    public void writeStaffData() {
        dm.writer("database/StaffData.csv", dm.generateStaffRecords(users.getAllStaff()));
    }

    public void writeStudentData() {
        dm.writer("database/StudentData.csv", dm.generateStudentRecords(users.getAllStudent()));
    }
}
