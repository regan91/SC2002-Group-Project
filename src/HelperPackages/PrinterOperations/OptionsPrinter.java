package HelperPackages.PrinterOperations;

import CAMs.Camp;
import RegistrationController.Participant;
import UserTypes.CommitteeMember;
import UserTypes.User;
import UserTypes.UserList;

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


    public void StudentLoginOptions(User loggedUser){
        System.out.println();
        System.out.println("Welcome user: " + loggedUser.getUserID());
        System.out.println("Student Options:");
        System.out.println("1. Camp Menu");
        System.out.println("2. Enquiries Menu");
        System.out.println("3. Change Password");
        System.out.println("4. Logout");
        System.out.println("Please enter an option:");

    }

    public void StaffCampMenuOptions(){

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

    public void StudentCampMenuOptions(){

        System.out.println();
        System.out.println("Menu Options:");
        System.out.println("1. View All Camps");
        System.out.println("2. View Joined Camps");
        System.out.println("3. View Camp Details");
        System.out.println("4. Join Camp");
        System.out.println("5. Leave Camp");
        System.out.println("6. Return to previous menu");
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


    public void suggestionPrivilegedOptions(){

        System.out.println();
        System.out.println("Suggestion Options:");
        System.out.println("1. Show All Suggestion");
        System.out.println("2. Show Suggestion");
        System.out.println("3. Approve Suggestion");
        System.out.println("4. Return to previous menu");
        System.out.println("Please enter an option:");

    }

    public void reportPrivilegedOptions(){

        System.out.println();
        System.out.println("Report Options:");
        System.out.println("1. Generate General Report");
        System.out.println("2. Generate Performance Report");
        System.out.println("3. Return to previous menu");
        System.out.println("Please enter an option:");

    }

    public void generateGenReport(Camp campDetails){
        System.out.println("General Report for Camp " + campDetails.getStaticDetails().getCampID());
        System.out.println("------");
        System.out.println("Camp Name                      :" + campDetails.getStaticDetails().getCampID());
        System.out.println("Camp Staff-In-Charge           :" + campDetails.getStaticDetails().getStaffIC());
        System.out.println("Camp Dates                     :" + campDetails.getDynamicDetails().getCampDates());
        System.out.println("Camp Registration Closing Date :" + campDetails.getDynamicDetails().getClosingDate());
        System.out.println("Camp Open Status               :" + campDetails.getDynamicDetails().getOpenStatus());
        System.out.println("Camp Location                  :" + campDetails.getDynamicDetails().getLocation());
        System.out.println("Camp Available Slots           :" + campDetails.getDynamicDetails().getAvailableSlots());
        System.out.println("Camp Committee Members         :" + campDetails.getDynamicDetails().getCurrentCommitteeNum());
        System.out.println("Camp Description               :" + campDetails.getDynamicDetails().getDescription());
        System.out.println("Camp Visibility                :" + campDetails.getDynamicDetails().getVisibility());
    }

    public void generatePerformanceReport(Camp campDetails, UserList userList){
        System.out.println("General Report for Camp " + campDetails.getStaticDetails().getCampID());
        System.out.println("------");
        System.out.println("Camp Name                      :" + campDetails.getStaticDetails().getCampID());
        System.out.println("Camp Staff-In-Charge           :" + campDetails.getStaticDetails().getStaffIC());
        System.out.println("Camp Dates                     :" + campDetails.getDynamicDetails().getCampDates());
        System.out.println("Camp Registration Closing Date :" + campDetails.getDynamicDetails().getClosingDate());
        System.out.println("Camp Open Status               :" + campDetails.getDynamicDetails().getOpenStatus());
        System.out.println("Camp Location                  :" + campDetails.getDynamicDetails().getLocation());
        System.out.println("Camp Available Slots           :" + campDetails.getDynamicDetails().getAvailableSlots());
        System.out.println("Camp Committee Members         :" + campDetails.getDynamicDetails().getCurrentCommitteeNum());
        System.out.println("Camp Committee Members         :" + campDetails.getDynamicDetails().getCurrentCommitteeNum());
        System.out.println("Camp Description               :" + campDetails.getDynamicDetails().getDescription());
        System.out.println("Camp Visibility                :" + campDetails.getDynamicDetails().getVisibility());
        System.out.println("-------");
        System.out.println("Committee Members: ");
        for (Participant partUser: campDetails.getStaticDetails().getRegistrations().getCommitteeMembers()){
            String userID = partUser.getID();
            CommitteeMember signedUp = (CommitteeMember) userList.getStudent(userID);
            System.out.println("Name: " + signedUp.getName());
            System.out.println("Points: " + signedUp.getPoints());
            System.out.println();
        }


    }
}
