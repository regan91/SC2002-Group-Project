package CAMs;

import HelperPackages.CampPrinter;
import HelperPackages.PrinterOperations.OptionsPrinter;
import HelperPackages.ProcessManagers.DataController;
import HelperPackages.PrinterOperations.LoginPrinter;
import UserTypes.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CampApp {
    static CampListController CampListBrain = new CampListController();
    public static UserList users = new UserList();

    private static DataController dc = new DataController();
    public static final OptionsPrinter optionPrinter = new OptionsPrinter();
    public static final LoginPrinter lg = new LoginPrinter();

    public static final Scanner campScanner = new Scanner(System.in);

    public static User loggedInAccount = null;
    public static void main(String[] args) {

        boolean failState = false;
        System.out.println("Beginning Power On Self Test...\n");
        System.out.println("Preparing data...\n");

        dc.loadStaffData();
        System.out.println("Loaded Staff records...");
        dc.loadStudentData();
        System.out.println("Loaded Student records...");

        System.out.println("Data has loaded, CAMs is ready!");

        optionPrinter.loginOptions();
        String loginInput = campScanner.nextLine();


        while (!failState){
            switch (loginInput){
                case "1": {
                    // Staff Login
                    lg.idPrompt();
                    String uid = campScanner.nextLine();
                    lg.passwordPrompt();
                    String pwd = campScanner.nextLine();

                    for (Staff staffRecord: users.getAllStaff()){
                        if ((staffRecord.login(uid, pwd))){
                            loggedInAccount = staffRecord;
                        }
                    }
                    if (loggedInAccount != null){
                        boolean loggedIn = true;

                        System.out.println("Login successful!");

                        optionPrinter.StaffLoginOptions(loggedInAccount);
                        String staffOption = campScanner.nextLine();

                        while (loggedIn){
                            switch(staffOption){
                                // Printing Staff Options
                                case "1": {
                                    // Staff Menu Options
                                    optionPrinter.StaffCampMenuOptions();
                                    CampPrinter cPrinter = new CampPrinter();
                                    String menuOption = campScanner.nextLine();

                                    while (!menuOption.contains("7")) {
                                        // Staff Camp Options
                                        switch (menuOption){
                                            case "1": {
                                                // basic camp details will only show camp ID, camp Name, camp Dates
                                                System.out.println("Showing all camps: ");
                                                ArrayList<Camp> campCollection = CampListBrain.getAllCamps();
                                                for (Camp campRec: campCollection){
                                                    cPrinter.printCampBasicDetails(campRec);
                                                }
                                                optionPrinter.StaffCampMenuOptions();
                                                menuOption = campScanner.nextLine();
                                                break;
                                            }
                                            case "2": {
                                                // printing out User created camps
                                                System.out.println("Showing created camps: ");
                                                ArrayList<Camp> campCollection = CampListBrain.getCreatedCamps(loggedInAccount.getUserID());
                                                for (Camp campRec: campCollection){
                                                    cPrinter.printCreatedCamp(campRec);
                                                }
                                                optionPrinter.StaffCampMenuOptions();
                                                menuOption = campScanner.nextLine();
                                                break;
                                            }
                                            case "3": {
                                                // printing out User created camps
                                                System.out.println("Please input camp ID: ");
                                                int desiredCamp = 0;
                                                try {
                                                    desiredCamp = Integer.parseInt(campScanner.nextLine());
                                                    System.out.println("Showing detailed information: ");
                                                    ArrayList<Camp> campCollection = CampListBrain.getCamp(desiredCamp);
                                                    for (Camp campRec: campCollection){
                                                        cPrinter.printCreatedCamp(campRec);
                                                    }
                                                }catch (Exception e){
                                                    System.out.println("Invalid camp ID");
                                                }
                                                optionPrinter.StaffCampMenuOptions();
                                                menuOption = campScanner.nextLine();
                                                break;
                                            }
                                            case "4": {
                                                String changeValue = "";
                                                System.out.println("Creating New Camp: ");
                                                System.out.println("Please Camp Name: ");
                                                changeValue = campScanner.nextLine();
                                                Staff staffMember = (Staff) loggedInAccount;
                                                CampListBrain.getList().addCamp(staffMember, changeValue);

                                                System.out.println("New Camp has been updated!");

                                                optionPrinter.StaffCampMenuOptions();
                                                menuOption = campScanner.nextLine();
                                                break;
                                            }
                                            case "5": {
                                                System.out.println("Please input camp ID: ");
                                                Camp desiredCamp = null;
                                                try {
                                                    ArrayList<Camp> obtainedCamps = CampListBrain.getCamp(Integer.parseInt(campScanner.nextLine()));
                                                    desiredCamp = obtainedCamps.get(0);
                                                }catch (Exception e){
                                                    System.out.println("Invalid camp ID");
                                                    optionPrinter.StaffCampMenuOptions();
                                                    menuOption = campScanner.nextLine();
                                                    break;
                                                }

                                                System.out.println("Editing camps details: ");
                                                optionPrinter.campEditOptions();
                                                String editOptions = campScanner.nextLine();
                                                while (!editOptions.contains("7")) {
                                                    optionPrinter.campEditOptions();
                                                    editOptions = campScanner.nextLine();
                                                    switch (editOptions){
                                                        case "1":{
                                                            System.out.println("Please enter new name: ");
                                                            String changeValue = campScanner.nextLine();
                                                            desiredCamp.getDynamicDetails().setName(changeValue);
                                                            System.out.println("Camp name has been updated!");
                                                            break;
                                                        }
                                                        case "2": {
                                                            // This has the LocalDate datatypes in play, seperating from normal parsing
                                                            System.out.println("Please enter start date: (DD-MM-YYYY)");
                                                            String startDate = campScanner.nextLine();
                                                            System.out.println("Please enter end date: (DD-MM-YYYY)");
                                                            String endDate = campScanner.nextLine();
                                                            String[] strippedStartDate = startDate.split("-");
                                                            String[] strippedEndDate = endDate.split("-");
                                                            try {
                                                                LocalDate dateStart = LocalDate.of(
                                                                        Integer.parseInt(strippedStartDate[2]),
                                                                        Integer.parseInt(strippedStartDate[1]),
                                                                        Integer.parseInt(strippedStartDate[0])
                                                                );
                                                                LocalDate dateEnd = LocalDate.of(
                                                                        Integer.parseInt(strippedEndDate[2]),
                                                                        Integer.parseInt(strippedEndDate[1]),
                                                                        Integer.parseInt(strippedEndDate[0])
                                                                );
                                                                desiredCamp.getDynamicDetails().setCampDates(dateStart, dateEnd);
                                                                System.out.println("Camp dates has been updated!");
                                                            } catch (Exception e){
                                                                System.out.println("Error: Unable to update camp dates!");
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                        case "3": {
                                                            // This has the LocalDate datatypes in play, seperating from normal parsing
                                                            System.out.println("Please registration closing date: (DD-MM-YYYY)");
                                                            String date = campScanner.nextLine();
                                                            String[] strippedStartDate = date.split("-");
                                                            try {
                                                                LocalDate stopDate = LocalDate.of(
                                                                        Integer.parseInt(strippedStartDate[2]),
                                                                        Integer.parseInt(strippedStartDate[1]),
                                                                        Integer.parseInt(strippedStartDate[0])
                                                                );
                                                                desiredCamp.getDynamicDetails().setClosingDate(stopDate);
                                                                System.out.println("Camp closing date has been updated!");
                                                            }catch (Exception e){
                                                                System.out.println("Error: Unable to update camp closing date!");
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                        case "4": {
                                                            System.out.println("Please set Camp's open status: [T/F]");
                                                            String changeValue = campScanner.nextLine();
                                                            if (changeValue.contains("T"))
                                                                desiredCamp.getDynamicDetails().setOpenStatus(true);
                                                            else desiredCamp.getDynamicDetails().setOpenStatus(false);

                                                            System.out.println("Camp status has been updated!");
                                                            break;
                                                        }
                                                        case "5": {
                                                            System.out.println("Please set Camp's location: ");
                                                            String changeValue = campScanner.nextLine();
                                                            desiredCamp.getDynamicDetails().setLocation(changeValue);

                                                            System.out.println("Camp status has been updated!");
                                                            break;
                                                        }
                                                        case "6": {
                                                            System.out.println("Please toggle Camp's visibility: [T/F]");
                                                            String changeValue = campScanner.nextLine();
                                                            if (changeValue.contains("T"))
                                                                desiredCamp.getDynamicDetails().setVisibility(true);
                                                            else desiredCamp.getDynamicDetails().setVisibility(false);

                                                            System.out.println("Camp visibility has been updated!");
                                                            break;
                                                        }

                                                        default:{
                                                            System.out.println("Invalid option");
                                                            break;
                                                        }
                                                    }
                                                    break;
                                                }
                                                System.out.println("Returning to previous menu...");
                                                optionPrinter.StaffCampMenuOptions();
                                                break;

                                            }
                                            case "6": {
                                                System.out.println("Deleting Camp: ");
                                                System.out.println("Please Camp ID: ");
                                                CampListBrain.getList().removeCamp(Integer.parseInt(campScanner.nextLine()));

                                                System.out.println("Camp has been updated!");

                                                optionPrinter.StaffCampMenuOptions();
                                                menuOption = campScanner.nextLine();
                                                break;
                                            }
                                            default:{
                                                System.out.println("Invalid option");
                                                optionPrinter.StaffCampMenuOptions();
                                                menuOption = campScanner.nextLine();
                                                break;
                                            }
                                        }
                                    }

                                    System.out.println("Returning to previous menu...");
                                    break;
                                }
                                case "2": {
                                    // Enquiries Menu
                                    System.out.println("Please input camp ID: ");
                                    Camp desiredCamp = null;
                                    try {
                                        ArrayList<Camp> campCollection = CampListBrain.getCamp(Integer.parseInt(campScanner.nextLine()));
                                        desiredCamp = campCollection.get(0);

                                    }catch (Exception e){
                                        System.out.println("Invalid camp ID");
                                        optionPrinter.StaffLoginOptions(loggedInAccount);
                                        staffOption = campScanner.nextLine();

                                        break;
                                    }

                                    optionPrinter.enquiriesPrivilegedOptions();
                                    String enquiryOption = campScanner.nextLine();

                                    while (!enquiryOption.contains("4")){
                                        optionPrinter.enquiriesPrivilegedOptions();
                                        enquiryOption = campScanner.nextLine();
                                        switch (enquiryOption){
                                            case "1": {
                                                System.out.println("Showing all Enquiries for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                desiredCamp.getStaticDetails().getEnquiries().viewEnquiriesRecords();
                                                break;
                                            }
                                            case "2": {
                                                System.out.println("Please input student ID: ");
                                                String studentID = campScanner.nextLine();

                                                System.out.println("Showing all Enquiries for Camp " + desiredCamp.getStaticDetails().getCampID() + " for Student ID: " + studentID);
                                                desiredCamp.getStaticDetails().getEnquiries().viewAllEnquiries(studentID);
                                                break;
                                            }
                                            case "3": {
                                                System.out.println("Please input student ID: ");
                                                String studentID = campScanner.nextLine();
                                                System.out.println("Please Enquiry ID: ");
                                                String enqID = campScanner.nextLine();
                                                System.out.println("Please input reply: ");
                                                String reply = campScanner.nextLine();


                                                System.out.println("Showing all Replies for Camp " + desiredCamp.getStaticDetails().getCampID() + " for Student ID: " + studentID);
                                                desiredCamp.getStaticDetails().getEnquiries().submitReply(studentID, Integer.parseInt(enqID), reply);
                                                break;
                                            }
                                            default:{
                                                System.out.println("Invalid option");
                                                break;
                                            }

                                        }
                                        System.out.println("Returning to previous menu...");
                                    }
                                    break;
                                }
                                case "3": {
                                    // Suggestions Menu
                                    System.out.println("Please input camp ID: ");
                                    Camp desiredCamp = null;
                                    try {
                                        ArrayList<Camp> campCollection = CampListBrain.getCamp(Integer.parseInt(campScanner.nextLine()));
                                        desiredCamp = campCollection.get(0);

                                    }catch (Exception e){
                                        System.out.println("Invalid camp ID");
                                        optionPrinter.StaffLoginOptions(loggedInAccount);
                                        staffOption = campScanner.nextLine();

                                        break;
                                    }

                                    optionPrinter.suggestionPrivilegedOptions();
                                    String suggestionOption = campScanner.nextLine();
                                    while (!suggestionOption.contains("4")){
                                        optionPrinter.enquiriesPrivilegedOptions();
                                        suggestionOption = campScanner.nextLine();
                                        switch (suggestionOption){
                                            case "1": {
                                                System.out.println("Showing all Suggestions for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                desiredCamp.getStaticDetails().getSuggestions().viewAllSuggestionsRecords();
                                                break;
                                            }
                                            case "2": {
                                                System.out.println("Please input student ID: ");
                                                String studentID = campScanner.nextLine();

                                                System.out.println("Showing all Suggestions for Camp " + desiredCamp.getStaticDetails().getCampID() + " for Student ID: " + studentID);
                                                desiredCamp.getStaticDetails().getSuggestions().viewSuggestions(studentID);
                                                break;
                                            }
                                            case "3": {
                                                System.out.println("Please input student ID: ");
                                                String studentID = campScanner.nextLine();

                                                System.out.println("Please input Suggestion ID: ");
                                                String sugID = campScanner.nextLine();

                                                System.out.println("Showing all Replies for Camp " + desiredCamp.getStaticDetails().getCampID() + " for Student ID: " + studentID);
                                                desiredCamp.getStaticDetails().getSuggestions().approveSuggestion(studentID, Integer.parseInt(sugID));
                                                break;
                                            }
                                            default:{
                                                System.out.println("Invalid option");
                                                break;
                                            }

                                        }
                                        System.out.println("Returning to previous menu...");
                                    }
                                    break;
                                }
                                case "4": {
                                    // Reports Menu

                                    System.out.println("Please input camp ID: ");
                                    Camp desiredCamp = null;
                                    try {
                                        ArrayList<Camp> campCollection = CampListBrain.getCamp(Integer.parseInt(campScanner.nextLine()));
                                        desiredCamp = campCollection.get(0);

                                    }catch (Exception e){
                                        System.out.println("Invalid camp ID");
                                        optionPrinter.StaffLoginOptions(loggedInAccount);
                                        staffOption = campScanner.nextLine();

                                        break;
                                    }
                                    optionPrinter.reportPrivilegedOptions();
                                    String reportOption = campScanner.nextLine();

                                    while (!reportOption.contains("3")){
                                        optionPrinter.enquiriesPrivilegedOptions();
                                        reportOption = campScanner.nextLine();
                                        switch (reportOption){
                                            case "1": {
                                                System.out.println("Generating General Report for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                optionPrinter.generateGenReport(desiredCamp);

                                                break;
                                            }
                                            case "2": {
                                                System.out.println("Generating Performance Report for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                optionPrinter.generatePerformanceReport(desiredCamp, users);
                                                break;
                                            }
                                            case "3": {
                                                System.out.println("Please input student ID: ");
                                                String studentID = campScanner.nextLine();

                                                System.out.println("Showing all Replies for Camp " + desiredCamp.getStaticDetails().getCampID() + " for Student ID: " + studentID);
                                                desiredCamp.getStaticDetails().getEnquiries().viewAllReply(studentID);
                                                break;
                                            }
                                            default:{
                                                System.out.println("Invalid option");
                                                break;
                                            }

                                        }
                                        System.out.println("Returning to previous menu...");
                                    }
                                    break;
                                }
                                case "5": {
                                    // Change Password Menu

                                    System.out.println("Please enter new password ");
                                    String newPass = campScanner.nextLine();

                                    loggedInAccount.passwordChange(newPass);
                                    break;

                                }
                                case "6": {
                                    // Staff Logging Out
                                    loggedIn = false;
                                    break;
                                }
                            }
                        }


                    }
                    else lg.failedLoginPrompt();
                    break;
                }
                case "2": {
                    // Student Login
                    lg.idPrompt();
                    String uid = campScanner.nextLine();
                    lg.passwordPrompt();
                    String pwd = campScanner.nextLine();

                    for (Student studentRecord: users.getAllStudent()){
                        if ((studentRecord.login(uid, pwd))){
                            loggedInAccount = studentRecord;
                        }
                    }
                    if (loggedInAccount != null){
                        boolean loggedIn = true;

                        System.out.println("Login successful!");

                        optionPrinter.StudentLoginOptions(loggedInAccount);
                        String studentOption = campScanner.nextLine();

                        while (loggedIn){
                            switch(studentOption){
                                // Printing Student Options
                                case "1": {
                                    // Student Menu Options
                                    optionPrinter.StudentCampMenuOptions();
                                    CampPrinter cPrinter = new CampPrinter();
                                    String menuOption = campScanner.nextLine();

                                    while (!menuOption.contains("7")) {
                                        // Student Camp Options
                                        switch (menuOption){
                                            case "1": {
                                                // basic camp details will only show camp ID, camp Name, camp Dates
                                                System.out.println("Showing all camps: ");
                                                ArrayList<Camp> campCollection = CampListBrain.getAllCamps(loggedInAccount.getFaculty());
                                                for (Camp campRec: campCollection){
                                                    cPrinter.printCampBasicDetails(campRec);
                                                }
                                                optionPrinter.StaffCampMenuOptions();
                                                menuOption = campScanner.nextLine();
                                                break;
                                            }
                                            case "2": {
                                                // printing out joined camps
                                                System.out.println("Showing joined camps: ");
                                                Student tempStd = (Student) loggedInAccount;
                                                ArrayList<Integer> retrievedCamps = tempStd.getRegisteredCamps();
                                                for (int campID: retrievedCamps){
                                                    ArrayList<Camp> retrievedCamp = CampListBrain.getCamp(campID);
                                                    Camp targetCamp = retrievedCamp.get(0);
                                                    cPrinter.printCampBasicDetails(targetCamp);
                                                }
                                                break;
                                            }
                                            case "3": {
                                                // printing out Camp details camps
                                                System.out.println("Please input camp ID: ");
                                                int desiredCamp = 0;
                                                try {
                                                    desiredCamp = Integer.parseInt(campScanner.nextLine());
                                                    System.out.println("Showing detailed information: ");
                                                    ArrayList<Camp> campCollection = CampListBrain.getCamp(desiredCamp);
                                                    for (Camp campRec: campCollection){
                                                        cPrinter.printCreatedCamp(campRec);
                                                    }
                                                }catch (Exception e){
                                                    System.out.println("Invalid camp ID");
                                                }
                                                optionPrinter.StaffCampMenuOptions();
                                                menuOption = campScanner.nextLine();
                                                break;
                                            }
                                            case "4": {
                                                // Joining Camp
                                                System.out.println("Please input camp ID: ");
                                                int desiredCamp = 0;
                                                try {
                                                    desiredCamp = Integer.parseInt(campScanner.nextLine());
                                                    System.out.println("Register as committee member?: [Y/N] ");
                                                    String committeeMember = campScanner.nextLine();
                                                    CommitteeMember stdMember = (CommitteeMember) loggedInAccount;
                                                    if (stdMember.getInChargeCamp() == -1){
                                                        boolean isCommitee = false;
                                                        if (committeeMember.contains("Y")) isCommitee = true;
                                                        ArrayList<Camp> campCollection = CampListBrain.getCamp(desiredCamp);
                                                        Camp targetCamp = campCollection.get(0);
                                                        targetCamp.getStaticDetails().getRegistrations().registerCamp(isCommitee, loggedInAccount.getUserID());

                                                        System.out.println("Registered for the camp!");
                                                    }else {
                                                        System.out.println("Not possible to register for the camp!");

                                                    }

                                                }catch (Exception e){
                                                    System.out.println("Invalid camp ID");
                                                }
                                                optionPrinter.StaffCampMenuOptions();
                                                menuOption = campScanner.nextLine();
                                                break;
                                            }
                                            case "5": {
                                                System.out.println("Please input camp ID: ");
                                                int desiredCamp = 0;
                                                try {
                                                    desiredCamp = Integer.parseInt(campScanner.nextLine());
                                                    CommitteeMember stdMember = (CommitteeMember) loggedInAccount;
                                                    if (stdMember.getInChargeCamp() != desiredCamp){
                                                        ArrayList<Camp> campCollection = CampListBrain.getCamp(desiredCamp);
                                                        Camp targetCamp = campCollection.get(0);
                                                        targetCamp.getStaticDetails().getRegistrations().withdrawCamp(loggedInAccount.getUserID());

                                                        System.out.println("Registered for the camp!");
                                                    }else {
                                                        System.out.println("Unable to withdraw from camp! You are committee member!");

                                                    }

                                                }catch (Exception e){
                                                    System.out.println("Invalid camp ID");
                                                }
                                                optionPrinter.StaffCampMenuOptions();
                                                menuOption = campScanner.nextLine();
                                                break;

                                            }
                                            default:{
                                                System.out.println("Invalid option");
                                                optionPrinter.StaffCampMenuOptions();
                                                menuOption = campScanner.nextLine();
                                                break;
                                            }
                                        }
                                    }

                                    System.out.println("Returning to previous menu...");
                                    break;
                                }
                                case "2": {
                                    // Enquiries Menu
                                    System.out.println("Please input camp ID: ");
                                    Camp desiredCamp = null;
                                    try {
                                        ArrayList<Camp> campCollection = CampListBrain.getCamp(Integer.parseInt(campScanner.nextLine()));
                                        desiredCamp = campCollection.get(0);

                                    }catch (Exception e){
                                        System.out.println("Invalid camp ID");
                                        optionPrinter.StaffLoginOptions(loggedInAccount);
                                        studentOption = campScanner.nextLine();

                                        break;
                                    }

                                    optionPrinter.enquiriesPrivilegedOptions();
                                    String enquiryOption = campScanner.nextLine();

                                    while (!enquiryOption.contains("4")){
                                        optionPrinter.enquiriesPrivilegedOptions();
                                        enquiryOption = campScanner.nextLine();
                                        switch (enquiryOption){
                                            case "1": {
                                                System.out.println("Showing all Enquiries for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                desiredCamp.getStaticDetails().getEnquiries().viewEnquiriesRecords();
                                                break;
                                            }
                                            case "2": {
                                                System.out.println("Please input student ID: ");
                                                String studentID = campScanner.nextLine();

                                                System.out.println("Showing all Enquiries for Camp " + desiredCamp.getStaticDetails().getCampID() + " for Student ID: " + studentID);
                                                desiredCamp.getStaticDetails().getEnquiries().viewAllEnquiries(studentID);
                                                break;
                                            }
                                            case "3": {
                                                System.out.println("Please input student ID: ");
                                                String studentID = campScanner.nextLine();
                                                System.out.println("Please Enquiry ID: ");
                                                String enqID = campScanner.nextLine();
                                                System.out.println("Please input reply: ");
                                                String reply = campScanner.nextLine();


                                                System.out.println("Showing all Replies for Camp " + desiredCamp.getStaticDetails().getCampID() + " for Student ID: " + studentID);
                                                desiredCamp.getStaticDetails().getEnquiries().submitReply(studentID, Integer.parseInt(enqID), reply);
                                                break;
                                            }
                                            default:{
                                                System.out.println("Invalid option");
                                                break;
                                            }

                                        }
                                        System.out.println("Returning to previous menu...");
                                    }
                                    break;
                                }
                                case "3": {
                                    // Suggestions Menu
                                    System.out.println("Please input camp ID: ");
                                    Camp desiredCamp = null;
                                    try {
                                        ArrayList<Camp> campCollection = CampListBrain.getCamp(Integer.parseInt(campScanner.nextLine()));
                                        desiredCamp = campCollection.get(0);

                                    }catch (Exception e){
                                        System.out.println("Invalid camp ID");
                                        optionPrinter.StaffLoginOptions(loggedInAccount);
                                        studentOption = campScanner.nextLine();

                                        break;
                                    }

                                    optionPrinter.suggestionPrivilegedOptions();
                                    String suggestionOption = campScanner.nextLine();
                                    while (!suggestionOption.contains("4")){
                                        optionPrinter.enquiriesPrivilegedOptions();
                                        suggestionOption = campScanner.nextLine();
                                        switch (suggestionOption){
                                            case "1": {
                                                System.out.println("Showing all Suggestions for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                desiredCamp.getStaticDetails().getSuggestions().viewAllSuggestionsRecords();
                                                break;
                                            }
                                            case "2": {
                                                System.out.println("Please input student ID: ");
                                                String studentID = campScanner.nextLine();

                                                System.out.println("Showing all Suggestions for Camp " + desiredCamp.getStaticDetails().getCampID() + " for Student ID: " + studentID);
                                                desiredCamp.getStaticDetails().getSuggestions().viewSuggestions(studentID);
                                                break;
                                            }
                                            case "3": {
                                                System.out.println("Please input student ID: ");
                                                String studentID = campScanner.nextLine();

                                                System.out.println("Please input Suggestion ID: ");
                                                String sugID = campScanner.nextLine();

                                                System.out.println("Showing all Replies for Camp " + desiredCamp.getStaticDetails().getCampID() + " for Student ID: " + studentID);
                                                desiredCamp.getStaticDetails().getSuggestions().approveSuggestion(studentID, Integer.parseInt(sugID));
                                                break;
                                            }
                                            default:{
                                                System.out.println("Invalid option");
                                                break;
                                            }

                                        }
                                        System.out.println("Returning to previous menu...");
                                    }
                                    break;
                                }
                                case "4": {
                                    // Reports Menu

                                    System.out.println("Please input camp ID: ");
                                    Camp desiredCamp = null;
                                    try {
                                        ArrayList<Camp> campCollection = CampListBrain.getCamp(Integer.parseInt(campScanner.nextLine()));
                                        desiredCamp = campCollection.get(0);

                                    }catch (Exception e){
                                        System.out.println("Invalid camp ID");
                                        optionPrinter.StaffLoginOptions(loggedInAccount);
                                        studentOption = campScanner.nextLine();

                                        break;
                                    }
                                    optionPrinter.reportPrivilegedOptions();
                                    String reportOption = campScanner.nextLine();

                                    while (!reportOption.contains("3")){
                                        optionPrinter.enquiriesPrivilegedOptions();
                                        reportOption = campScanner.nextLine();
                                        switch (reportOption){
                                            case "1": {
                                                System.out.println("Generating General Report for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                optionPrinter.generateGenReport(desiredCamp);

                                                break;
                                            }
                                            case "2": {
                                                System.out.println("Generating Performance Report for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                optionPrinter.generatePerformanceReport(desiredCamp, users);
                                                break;
                                            }
                                            case "3": {
                                                System.out.println("Please input student ID: ");
                                                String studentID = campScanner.nextLine();

                                                System.out.println("Showing all Replies for Camp " + desiredCamp.getStaticDetails().getCampID() + " for Student ID: " + studentID);
                                                desiredCamp.getStaticDetails().getEnquiries().viewAllReply(studentID);
                                                break;
                                            }
                                            default:{
                                                System.out.println("Invalid option");
                                                break;
                                            }

                                        }
                                        System.out.println("Returning to previous menu...");
                                    }
                                    break;
                                }
                                case "5": {
                                    // Change Password Menu

                                    System.out.println("Please enter new password ");
                                    String newPass = campScanner.nextLine();

                                    loggedInAccount.passwordChange(newPass);
                                    break;

                                }
                                case "6": {
                                    // Staff Logging Out
                                    loggedIn = false;
                                    break;
                                }
                            }
                        }


                    }
                    else lg.failedLoginPrompt();
                    break;
                }
                case "3": {
                    failState = true;
                    break;
                }
                default: {

                    System.out.println();
                    System.out.println("Options:");
                    System.out.println("1. Staff Login");
                    System.out.println("2. Student Login");
                    System.out.println("3. Exit");
                    System.out.println("Please enter an option:");
                    loginInput = campScanner.nextLine();
                    break;
                }
            }
        }


    }
}
