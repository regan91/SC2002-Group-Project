package HelperPackages.ProcessManagers;

import HelperPackages.ProcessManagers.Interfaces.IFlowManager;

import static CAMs.CampApp.campScanner;

public class FlowManager implements IFlowManager {
    public void loginView() {
        boolean failState = false;
        System.out.println("Invalid choice, please select again: ");
        System.out.println("Options:");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.println("Please enter an option:");
        String userInput = campScanner.nextLine();

        while (!failState){
            switch (userInput){
                case "1":
                    boolean isLoggedIn;
                {
                    LoginManager lm = new LoginManager();
                    isLoggedIn = lm.staffLogin();
                    break;
                }
                case "2": {
                    LoginManager lm = new LoginManager();
                    isLoggedIn = lm.studentLogin();
                    break;
                }
                case "3":{
                    System.out.println("System is shutting down...");
//                    dc.writeStaffData();
//                    dc.writeStudentData();
                    failState = true;
                    break;
                }
                default: {
                    System.out.println("Invalid choice, please select again: ");
                    System.out.println("Options:");
                    System.out.println("1. Login");
                    System.out.println("2. Exit");
                    System.out.println("Please enter an option:");
                    userInput = campScanner.nextLine();
                    break;
                }
            }
        }
    }

    public boolean studentView() {
        return false;
    }

    public boolean staffView() {
        return false;
    }

    public boolean committeeView() {
        return false;
    }
}
