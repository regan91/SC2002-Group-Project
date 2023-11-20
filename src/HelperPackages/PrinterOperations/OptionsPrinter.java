package HelperPackages.PrinterOperations;

import CAMs.Camp;
import RegistrationController.Participant;
import UserTypes.CommitteeMember;
import UserTypes.Student;
import UserTypes.User;
import UserTypes.UserList;

import java.time.LocalDate;

public class OptionsPrinter {

    public void idPrompt(){
        System.out.println();
        System.out.println("Please enter your user ID: ");
    }

    public void pwdPrompt(){
        System.out.println();
        System.out.println("Please enter your password: ");
    }


    public void failedLoginPrompt(){
        System.out.println();
        System.out.println("Error: Incorrect user ID or password! ");
    }
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
        System.out.println("Welcome user: " + loggedUser.getName());
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
        System.out.println("Welcome user: " + loggedUser.getName());
        System.out.println("Student Options:");
        System.out.println("1. Camp Menu");
        System.out.println("2. Enquiries Menu");
        System.out.println("3. Change Password");
        System.out.println("4. Logout");
        System.out.println("Please enter an option:");

    }

    public void committeeLoginOptions(User loggedUser){
        System.out.println();
        System.out.println("Welcome user: " + loggedUser.getName());
        System.out.println("Student Options:");
        System.out.println("1. Camp Menu");
        System.out.println("2. Enquiries Menu");
        System.out.println("3. Change Password");
        System.out.println(" ---- ");
        System.out.println("Committee Member Options:");
        System.out.println("4. Suggestions Menu");
        System.out.println("5. Generate Report");
        System.out.println("6. Logout");
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
        System.out.println("5. Camp Max Participants");
        System.out.println("6. Location");
        System.out.println("7. Toggle Visibility");
        System.out.println("8. Camp Description");
        System.out.println("9. Return to previous menu");
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

    public void enquiriesCommitteeOptions(){

        System.out.println();
        System.out.println("Enquiries Options:");
        System.out.println("1. Show All Enquiries");
        System.out.println("2. Show Enquiry");
        System.out.println("3. Create Reply");
        System.out.println("4. Edit Reply");
        System.out.println("5. Return to previous menu");
        System.out.println("Please enter an option:");

    }

    public void enquiriesOptions(){

        System.out.println();
        System.out.println("Enquiries Options:");
        System.out.println("1. Show All Enquiries");
        System.out.println("2. Show Enquiry");
        System.out.println("3. Create Enquiry");
        System.out.println("4. Edit Enquiry");
        System.out.println("5. Delete Enquiry");
        System.out.println("6. Return to previous menu");
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

    public void suggestionOptions(){

        System.out.println();
        System.out.println("Suggestion Options:");
        System.out.println("1. Show All Suggestions");
        System.out.println("2. Show Suggestion");
        System.out.println("3. Create Suggestion");
        System.out.println("4. Edit Suggestion");
        System.out.println("5. Delete Suggestion");
        System.out.println("6. Return to previous menu");
        System.out.println("Please enter an option:");

    }

    public void reportPrivilegedOptions(){

        System.out.println();
        System.out.println("Report Options:");
        System.out.println("1. Generate General Report");
        System.out.println("2. Generate Performance Report");
        System.out.println("3. Generate Custom Report");
        System.out.println("4. Return to previous menu");
        System.out.println("Please enter an option:");

    }
}
