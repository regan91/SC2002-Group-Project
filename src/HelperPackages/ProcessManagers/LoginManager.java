package HelperPackages.ProcessManagers;

import HelperPackages.ProcessManagers.Interfaces.ILoginManager;
import UserTypes.Staff;
import UserTypes.Student;

import static CAMs.CampApp.*;

public class LoginManager implements ILoginManager {
    public boolean staffLogin() {
        lg.idPrompt();
        String uid = campScanner.nextLine();
        lg.passwordPrompt();
        String pwd = campScanner.nextLine();
        
        for (Staff staffRecord: users.getAllStaff()){
            if ((staffRecord.login(uid, pwd))){
                lg.successStaffLogin();
                return true;
            }
        }
        lg.failedLoginPrompt();
        return false;
    }

    public boolean studentLogin() {
        lg.idPrompt();
        String uid = campScanner.nextLine();
        lg.passwordPrompt();
        String pwd = campScanner.nextLine();

        for (Student stdRecord: users.getAllStudent()){
            if ((stdRecord.login(uid, pwd))){
                lg.successStudentLogin(false);
                return true;
            }
        }
        lg.failedLoginPrompt();
        return false;
    }
}
