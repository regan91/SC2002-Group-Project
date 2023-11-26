package HelperPackages.ProcessManagers;

import HelperPackages.ProcessManagers.Interfaces.IDataManager;
import HelperPackages.Serializer.BackupUtility;
import HelperPackages.Serializer.SerializationUtil;
import UserTypes.Staff;
import UserTypes.Student;
import UserTypes.User;

import java.util.ArrayList;
import java.util.List;

public class DataManager implements IDataManager {
    public List<String> reader(String filePath, String alternative){
        ArrayList<String> retrievedData;
        try{
            retrievedData = SerializationUtil.reader(filePath);
        }catch (Exception e){
            System.out.println("Error: Serializer was not able to read from file, using backup");
            retrievedData = BackupUtility.reader(alternative);
        }
        return retrievedData;
    }

    public void writer(String filePath, List<String> dataToStore){
        try {
            SerializationUtil.writer(filePath, dataToStore);
        } catch (Exception e) {
            System.out.println("Error: Serializer was not able to write to file");
        }
    }

    public User formatter(String unSortedData, boolean isStaff){
        String[] splitSRecord = unSortedData.split(",");
        if (isStaff) {
            Staff newRecord = new Staff(
                    splitSRecord[0].strip(),
                    splitSRecord[1].split("@")[0].strip(),
                    splitSRecord[2].strip()
                    );
            try{
                newRecord.setPassword(splitSRecord[3].strip());
            }catch (Exception e){
                System.out.println("Debug: Record has no password");
            }
            return newRecord;
        }
        else {
            Student newRecord = new Student(
                    splitSRecord[0].strip(),
                    splitSRecord[1].split("@")[0].strip(),
                    splitSRecord[2].strip()
            );
            try{
                newRecord.setPassword(splitSRecord[3].strip());
            }catch (Exception e){
                System.out.println("Debug: Record has no password");
            }

            return newRecord;
        }
    }

    public ArrayList<String> generateStaffRecords(ArrayList<Staff> targetList) {
        ArrayList<String> convertedList = new ArrayList<>();
        for (User dataRecord: targetList){
            String emailAddress = "ntu.edu.sg";
            String combinedData = dataRecord.getName() + "," + dataRecord.getUserID()+ "@" + emailAddress + "," + dataRecord.getFaculty() + "," + dataRecord.getPassword();
            convertedList.add(combinedData);
        }
        return convertedList;
    }
    public ArrayList<String> generateStudentRecords(ArrayList<Student> targetList) {
        ArrayList<String> convertedList = new ArrayList<>();
        for (User dataRecord: targetList){
            String emailAddress = "e.ntu.edu.sg";
            String combinedData = dataRecord.getName() + "," + dataRecord.getUserID()+ "@" + emailAddress + "," + dataRecord.getFaculty() + "," + dataRecord.getPassword();
            convertedList.add(combinedData);
        }
        return convertedList;
    }

}
