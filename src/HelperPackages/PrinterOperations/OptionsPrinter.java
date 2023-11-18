package HelperPackages.PrinterOperations;

import UserTypes.User;

public class OptionsPrinter {
    public void loginOptions(){
        System.out.println();
        System.out.println("Options:");
        System.out.println("1. Staff Login");
        System.out.println("2. Student Login");
        System.out.println("3. Exit");
        System.out.println("Please enter an option:");
    }
    public void StaffLoginOptions(User loggedUser){
        System.out.println();
        System.out.println("Welcome user: " + loggedUser.getUserID());

        System.out.println("Staff Options:");
        System.out.println("1. Camp Menu");
        System.out.println("2. Enquiries Menu");
        System.out.println("3. Suggestions Menu");
        System.out.println("4. Report Menu");
        System.out.println("5. Change Password");
        System.out.println("6. Logout");
        System.out.println("Please enter an option:");

    }
    public void StaffMenuOptions(){

        System.out.println();
        System.out.println("Menu Options:");
        System.out.println("1. View All Camps");
        System.out.println("2. View Created Camps");
        System.out.println("3. View Camp Details");
        System.out.println("4. Create New Camp");
        System.out.println("5. Edit Camp Details");
        System.out.println("6. Delete Camp");
        System.out.println("7. Return to previous menu");
        System.out.println("Please enter an option:");
    }

    public void campEditOptions(){

        System.out.println();
        System.out.println("Edit Options:");
        System.out.println("1. Camp Name");
        System.out.println("2. Camp Dates");
        System.out.println("3. Camp Registration Closing Date");
        System.out.println("4. Camp Open Status");
        System.out.println("5. Location");
        System.out.println("6. Toggle Visibility");
        System.out.println("7. Return to previous menu");
        System.out.println("Please enter an option:");

    }

    public void enquiriesPrivilegedOptions(){

        System.out.println();
        System.out.println("Enquiries Options:");
        System.out.println("1. Show All Enquiries");
        System.out.println("2. Show Enquiry");
        System.out.println("3. Reply Enquiry");
        System.out.println("4. Return to previous menu");
        System.out.println("Please enter an option:");

    }
}
