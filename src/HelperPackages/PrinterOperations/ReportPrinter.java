package HelperPackages.PrinterOperations;

import CAMs.Camp;
import RegistrationController.Participant;
import UserTypes.CommitteeMember;
import UserTypes.Student;
import UserTypes.UserList;

import java.time.LocalDate;

public class ReportPrinter {


    public void generateCustomReport(Camp campDetails, UserList userList, int filters){
        System.out.println("General Report for Camp " + campDetails.getDynamicDetails().getCampName());
        System.out.println("------");
        System.out.println("Camp Name                      :" + campDetails.getDynamicDetails().getCampName());
        System.out.println("Camp Staff-In-Charge           :" + campDetails.getStaticDetails().getStaffIC().getUserID());
        System.out.println("Camp Dates                     :(YYYY-MM-DD)");
        if (campDetails.getDynamicDetails().getCampDates().isEmpty()){
            System.out.println();
        } else {
            for (LocalDate date : campDetails.getDynamicDetails().getCampDates()) {
                System.out.println(" " + date.toString());
            }
        }
        if (filters == 1) {
            System.out.println("-------");
            System.out.println("Attendee Members: ");
            for (Participant partUser : campDetails.getStaticDetails().getRegistrations().getAttendeeMembers()) {
                String userID = partUser.getID();
                String studentName = userList.getStudent(userID).getName();
                System.out.println("       Name: " + studentName);
                System.out.println();
            }
        }
        if (filters == 2)
        {
            System.out.println("-------");
            System.out.println("Committee Members: ");
            for (Participant partUser: campDetails.getStaticDetails().getRegistrations().getCommitteeMembers()){
                String userID = partUser.getID();
                String studentName = userList.getStudent(userID).getName();
                CommitteeMember targetMember = (CommitteeMember) userList.getStudent(userID);
                System.out.println("       Name: " + studentName);
                System.out.println("       Points: " + targetMember.getPoints());
                System.out.println();
            }
        }

        if (filters == 3) {
            System.out.println("-------");
            System.out.println("Faculty Members: ");
            for (Participant partUser : campDetails.getStaticDetails().getRegistrations().getAttendeeMembers()) {

                String campFaculty = campDetails.getStaticDetails().getStaffIC().getFaculty();

                String userID = partUser.getID();
                Student targetMember = userList.getStudent(userID);
                if (targetMember.getFaculty().equals(campFaculty)) {

                    System.out.println("       Name: " + targetMember.getName());
                    System.out.println();
                }
            }
            for (Participant partUser : campDetails.getStaticDetails().getRegistrations().getCommitteeMembers()) {
                String campFaculty = campDetails.getStaticDetails().getStaffIC().getFaculty();

                String userID = partUser.getID();
                Student targetMember = userList.getStudent(userID);
                if (targetMember.getFaculty().equals(campFaculty)) {

                    System.out.println("       Name: " + targetMember.getName());
                    System.out.println();
                }
            }
        }
    }
    public void generateGenReport(Camp campDetails, UserList userList){
        System.out.println("Performance Report for Camp " + campDetails.getDynamicDetails().getCampName());
        System.out.println("------");
        System.out.println("Camp Name                      :" + campDetails.getStaticDetails().getCampID());
        System.out.println("Camp Staff-In-Charge           :" + campDetails.getStaticDetails().getStaffIC().getUserID());
        System.out.println("Camp Dates                     :(YYYY-MM-DD)");
        if (campDetails.getDynamicDetails().getCampDates().isEmpty()){
            System.out.println();
        } else {
            for (LocalDate date : campDetails.getDynamicDetails().getCampDates()) {
                System.out.println(" " + date.toString());
            }
        }
        System.out.println("Camp Registration Closing Date :" + campDetails.getDynamicDetails().getClosingDate());
        System.out.println("Camp Open Status               :" + campDetails.getDynamicDetails().getOpenStatus());
        System.out.println("Camp Location                  :" + campDetails.getDynamicDetails().getLocation());
        System.out.println("Camp Available Slots           :" + campDetails.getDynamicDetails().getAvailableSlots());
        System.out.println("Camp Committee Members Slots   :" + campDetails.getDynamicDetails().getCurrentCommitteeNum());
        System.out.println("Camp Description               :" + campDetails.getDynamicDetails().getDescription());
        System.out.println("Camp Visibility                :" + campDetails.getDynamicDetails().getVisibility());
        System.out.println("-------");
        System.out.println("Attendee Members: ");
        for (Participant partUser: campDetails.getStaticDetails().getRegistrations().getAttendeeMembers()){
            String userID = partUser.getID();
            String studentName = userList.getStudent(userID).getName();
            System.out.println("       Name: " + studentName);
            System.out.println();
        }
        System.out.println("-------");
        System.out.println("Committee Members: ");
        for (Participant partUser: campDetails.getStaticDetails().getRegistrations().getCommitteeMembers()){
            String userID = partUser.getID();
            String studentName = userList.getStudent(userID).getName();
            CommitteeMember targetMember = (CommitteeMember) userList.getStudent(userID);
            System.out.println("       Name: " + studentName);
            System.out.println("       Points: " + targetMember.getPoints());
            System.out.println();
        }
    }

    public void generatePerformanceReport(Camp campDetails, UserList userList){
        System.out.println("Performance Report for Camp " + campDetails.getDynamicDetails().getCampName());
        System.out.println("------");
        System.out.println("Camp Name                      :" + campDetails.getStaticDetails().getCampID());
        System.out.println("Camp Staff-In-Charge           :" + campDetails.getStaticDetails().getStaffIC().getUserID());
        System.out.println("Camp Dates                     :(YYYY-MM-DD)");
        if (campDetails.getDynamicDetails().getCampDates().isEmpty()){
            System.out.println();
        } else {
            for (LocalDate date : campDetails.getDynamicDetails().getCampDates()) {
                System.out.println(" " + date.toString());
            }
        }
        System.out.println("Camp Registration Closing Date :" + campDetails.getDynamicDetails().getClosingDate());
        System.out.println("Camp Open Status               :" + campDetails.getDynamicDetails().getOpenStatus());
        System.out.println("Camp Location                  :" + campDetails.getDynamicDetails().getLocation());
        System.out.println("Camp Available Slots           :" + campDetails.getDynamicDetails().getAvailableSlots());
        System.out.println("Camp Committee Members         :" + campDetails.getDynamicDetails().getCurrentCommitteeNum());
        System.out.println("Camp Description               :" + campDetails.getDynamicDetails().getDescription());
        System.out.println("Camp Visibility                :" + campDetails.getDynamicDetails().getVisibility());
        System.out.println("-------");
        System.out.println("Attendee Members: ");
        for (Participant partUser: campDetails.getStaticDetails().getRegistrations().getAttendeeMembers()){
            String userID = partUser.getID();
            String studentName = userList.getStudent(userID).getName();
            System.out.println("       Name: " + studentName);
            System.out.println();
        }
        System.out.println("-------");
        System.out.println("Committee Members: ");
        for (Participant partUser: campDetails.getStaticDetails().getRegistrations().getCommitteeMembers()){
            String userID = partUser.getID();
            String studentName = userList.getStudent(userID).getName();
            CommitteeMember targetMember = (CommitteeMember) userList.getStudent(userID);
            System.out.println("       Name: " + studentName);
            System.out.println("       Points: " + targetMember.getPoints());
            System.out.println();
        }


    }

    public void customReportOptions(){
        System.out.println();
        System.out.println("Report Filters:");
        System.out.println("1. Show Attendees");
        System.out.println("2. Show Committee Members");
        System.out.println("3. Show Faculty");
        System.out.println("4. Return to previous menu");
        System.out.println("Please enter an option:");
    }
}
