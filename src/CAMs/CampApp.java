package CAMs;

import HelperPackages.PrinterOperations.CampDetailsPrinter;
import HelperPackages.PrinterOperations.OptionsPrinter;
import HelperPackages.ProcessManagers.DataController;
import UserTypes.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CampApp {
    static CampListController CampListBrain = new CampListController();
    public static UserList users = new UserList();
    private static DataController dataController = new DataController();
    public static final OptionsPrinter optionPrinter = new OptionsPrinter();
    public static final Scanner campScanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean failState = false;
        System.out.println("Beginning Power On Self Test...\n");
        System.out.println("Preparing data...\n");

        dataController.loadStaffData();
        System.out.println("Loaded Staff records...");
        dataController.loadStudentData();
        System.out.println("Loaded Student records...");

        System.out.println("Data has loaded, CAMs is ready!");


        while (!failState){
            User loggedInAccount = null;
            optionPrinter.loginOptions();
            String loginInput = campScanner.nextLine();
            switch (loginInput){
                case "1": {
                    // Staff Login
                    optionPrinter.idPrompt();
                    String uid = campScanner.nextLine();
                    optionPrinter.pwdPrompt();
                    String pwd = campScanner.nextLine();

                    for (Staff staffRecord: users.getAllStaff()){
                        if ((staffRecord.login(uid, pwd))){
                            loggedInAccount = staffRecord;
                        }
                    }
                    if (loggedInAccount != null){
                        boolean loggedIn = true;

                        System.out.println("Login successful!");

                        while (loggedIn){
                            optionPrinter.StaffLoginOptions(loggedInAccount);
                            String staffOption = campScanner.nextLine();
                            switch(staffOption){
                                // Printing Staff Options
                                case "1": {
                                    // Staff Menu Options
                                    optionPrinter.StaffCampMenuOptions();
                                    CampDetailsPrinter cPrinter = new CampDetailsPrinter();
                                    String menuOption = campScanner.nextLine();

                                    while (!menuOption.contains("7")) {
                                        // Staff Camp Options
                                        switch (menuOption){
                                            case "1": {
                                                // basic camp details will only show camp ID, camp Name, camp Dates
                                                System.out.println("Showing all camps: ");
                                                System.out.println();
                                                ArrayList<Camp> campCollection = CampListBrain.getAllCamps();
                                                for (Camp campRec: campCollection){
                                                    cPrinter.printCampBasicDetails(campRec);
                                                }
                                                break;
                                            }
                                            case "2": {
                                                // printing out User created camps
                                                System.out.println("Showing created camps: ");
                                                System.out.println();
                                                ArrayList<Camp> campCollection = CampListBrain.getCreatedCamps(loggedInAccount.getUserID());
                                                for (Camp campRec: campCollection){
                                                    cPrinter.printCreatedCamp(campRec);
                                                }
                                                break;
                                            }
                                            case "3": {
                                                // printing out specific camps
                                                System.out.println("Please input camp ID: ");
                                                int desiredCamp = 0;
                                                try {
                                                    desiredCamp = Integer.parseInt(campScanner.nextLine());
                                                    System.out.println("Showing detailed information: ");
                                                    System.out.println();
                                                    ArrayList<Camp> campCollection = CampListBrain.getCamp(desiredCamp);
                                                    for (Camp campRec: campCollection){
                                                        cPrinter.printDetailedCamp(campRec);
                                                    }
                                                }catch (Exception e){
                                                    System.out.println("Invalid camp ID");
                                                }
                                                break;
                                            }
                                            case "4": {
                                                // Creating new camp
                                                String changeValue = "";
                                                System.out.println("Creating New Camp: ");
                                                System.out.println("Please Camp Name: ");
                                                changeValue = campScanner.nextLine();
                                                Staff staffMember = (Staff) loggedInAccount;
                                                CampListBrain.getList().addCamp(staffMember, changeValue);

                                                System.out.println("New Camp has been updated!");

                                                break;
                                            }
                                            case "5": {
                                                // Editing Camp Details
                                                System.out.println("Please input camp ID: ");
                                                Camp desiredCamp = null;
                                                try {
                                                    ArrayList<Camp> obtainedCamps = CampListBrain.getCamp(Integer.parseInt(campScanner.nextLine()));
                                                    desiredCamp = obtainedCamps.get(0);
                                                }catch (Exception e){
                                                    System.out.println("Invalid camp ID");
                                                    break;
                                                }

                                                System.out.println("Editing camps details: ");
                                                optionPrinter.campEditOptions();
                                                String editOptions = campScanner.nextLine();
                                                while (!editOptions.contains("8")) {
                                                    switch (editOptions){
                                                        case "1":{
                                                            //Editing Camp Name
                                                            System.out.println("Please enter new name: ");
                                                            String changeValue = campScanner.nextLine();
                                                            desiredCamp.getDynamicDetails().setName(changeValue);
                                                            System.out.println("Camp name has been updated!");
                                                            break;
                                                        }
                                                        case "2": {
                                                            // Editing Dates of Camp
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
                                                            // Editing Camp Reg Closing Date
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
                                                            // Toggling Open Status
                                                            System.out.println("Please set Camp's open status: [T/F]");
                                                            String changeValue = campScanner.nextLine();
                                                            if (changeValue.contains("T"))
                                                                desiredCamp.getDynamicDetails().setOpenStatus(true);
                                                            else desiredCamp.getDynamicDetails().setOpenStatus(false);

                                                            System.out.println("Camp status has been updated!");
                                                            break;
                                                        }
                                                        case "5": {
                                                            // Max Participants
                                                            System.out.println("Please set Camp's Max Number of Participants: ");
                                                            String changeValue = campScanner.nextLine();
                                                            desiredCamp.getDynamicDetails().setMaxMember(Integer.parseInt(changeValue));

                                                            System.out.println("Camp Max Participants has been updated!");
                                                            break;
                                                        }
                                                        case "6": {
                                                            // Editing Location
                                                            System.out.println("Please set Camp's location: ");
                                                            String changeValue = campScanner.nextLine();
                                                            desiredCamp.getDynamicDetails().setLocation(changeValue);

                                                            System.out.println("Camp loation has been updated!");
                                                            break;
                                                        }
                                                        case "7": {
                                                            // Toggling Visibility
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
                                                    optionPrinter.campEditOptions();
                                                    editOptions = campScanner.nextLine();
                                                }
                                                System.out.println("Returning to previous menu...");
                                                optionPrinter.StaffCampMenuOptions();
                                                break;

                                            }
                                            case "6": {
                                                // Deleting Camp
                                                System.out.println("Deleting Camp: ");
                                                System.out.println("Please Camp ID: ");
                                                CampListBrain.getList().removeCamp(Integer.parseInt(campScanner.nextLine()));

                                                System.out.println("Camp has been updated!");
                                                break;
                                            }
                                            default:{
                                                System.out.println("Invalid option");
                                                break;
                                            }
                                        }
                                        optionPrinter.StaffCampMenuOptions();
                                        menuOption = campScanner.nextLine();
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
                                        break;
                                    }

                                    optionPrinter.enquiriesPrivilegedOptions();
                                    String enquiryOption = campScanner.nextLine();

                                    while (!enquiryOption.contains("4")){
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
                                                desiredCamp.getStaticDetails().getEnquiries().submitReply(loggedInAccount.getUserID(),Integer.parseInt(enqID), reply);
                                                break;
                                            }
                                            default:{
                                                System.out.println("Invalid option");
                                                break;
                                            }

                                        }
                                        optionPrinter.enquiriesPrivilegedOptions();
                                        enquiryOption = campScanner.nextLine();
                                    }
                                    System.out.println("Returning to previous menu...");
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

                                        break;
                                    }

                                    optionPrinter.suggestionPrivilegedOptions();
                                    String suggestionOption = campScanner.nextLine();
                                    while (!suggestionOption.contains("4")){
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
                                                System.out.println("Please input Suggestion ID: ");
                                                String sugID = campScanner.nextLine();

                                                System.out.println("Showing all Replies for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                desiredCamp.getStaticDetails().getSuggestions().approveSuggestion(Integer.parseInt(sugID));
                                                break;
                                            }
                                            default:{
                                                System.out.println("Invalid option");
                                                break;
                                            }

                                        }
                                        optionPrinter.suggestionPrivilegedOptions();
                                        suggestionOption = campScanner.nextLine();
                                    }
                                    System.out.println("Returning to previous menu...");
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
                                        optionPrinter.reportPrivilegedOptions();
                                        reportOption = campScanner.nextLine();
                                    }
                                    System.out.println("Returning to previous menu...");
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
                    else optionPrinter.failedLoginPrompt();
                    break;
                }
                case "2": {
                    // Student Login
                    optionPrinter.idPrompt();
                    String uid = campScanner.nextLine();
                    optionPrinter.pwdPrompt();
                    String pwd = campScanner.nextLine();

                    for (Student studentRecord: users.getAllStudent()){
                        if ((studentRecord.login(uid, pwd))){
                            loggedInAccount = studentRecord;
                        }
                    }
                    if (loggedInAccount != null){
                        boolean loggedIn = true;

                        System.out.println("Login successful!");
                        Student tempStudent = (Student) loggedInAccount;
                        if (!tempStudent.getIsCommittee()) {
                            // NOT AN IC
                            while (loggedIn){
                                optionPrinter.StudentLoginOptions(loggedInAccount);
                                String studentOption = campScanner.nextLine();
                                switch(studentOption){
                                    // Printing Student Options
                                    case "1": {
                                        // Student Menu Options
                                        optionPrinter.StudentCampMenuOptions();
                                        CampDetailsPrinter cPrinter = new CampDetailsPrinter();
                                        String menuOption = campScanner.nextLine();

                                        while (!menuOption.contains("6")) {
                                            // Student Camp Options
                                            switch (menuOption){
                                                case "1": {
                                                    // basic camp details will only show camp ID, camp Name, camp Dates
                                                    System.out.println("Showing all camps: ");
                                                    ArrayList<Camp> campCollection = CampListBrain.getAllCamps(loggedInAccount.getFaculty());
                                                    for (Camp campRec: campCollection){
                                                        cPrinter.printCampBasicDetails(campRec);
                                                    }
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
                                                    break;
                                                }
                                                case "4": {
                                                    // Joining Camp
                                                    System.out.println("Please input camp ID: ");
                                                    int desiredCamp = 0;
                                                    try {
                                                        desiredCamp = Integer.parseInt(campScanner.nextLine());

                                                        ArrayList<Camp> campCollection = CampListBrain.getCamp(desiredCamp);
                                                        Camp targetCamp = campCollection.get(0);
                                                        if (targetCamp.getStaticDetails().getRegistrations().isBlockedMember(loggedInAccount.getUserID())){
                                                            // if the person is blocked
                                                            System.out.println("You have withdrawn from the camp!");
                                                            break;
                                                        }
                                                        System.out.println("Register as committee member?: [Y/N] ");
                                                        String committeeMember = campScanner.nextLine();
                                                        if (!((Student) loggedInAccount).getIsCommittee()){
                                                            if (committeeMember.contains("Y")) {
                                                                if (targetCamp.getDynamicDetails().getCurrentCommitteeNum() > 0){
                                                                    targetCamp.getStaticDetails().getRegistrations().registerCamp(true, loggedInAccount.getUserID());
                                                                    targetCamp.getDynamicDetails().decCurrentCommitteNum();
                                                                    System.out.println("Registered for the camp as Committee Member!");
                                                                    ((Student) loggedInAccount).setIsCommittee(desiredCamp);
                                                                    ((Student) loggedInAccount).addRegisteredCamp(targetCamp.getStaticDetails().getCampID());
                                                                    break;
                                                                }
                                                            }else {
                                                                if (targetCamp.getDynamicDetails().getAvailableSlots() > 0){
                                                                targetCamp.getStaticDetails().getRegistrations().registerCamp(false, loggedInAccount.getUserID());
                                                                targetCamp.getDynamicDetails().decMemberNum();
                                                                System.out.println("Registered for the camp as participant!");}
                                                                ((Student) loggedInAccount).addRegisteredCamp(targetCamp.getStaticDetails().getCampID());
                                                                break;
                                                            }
                                                        }
                                                        else{
                                                            System.out.println("Not possible to register for the camp!");
                                                        }

                                                    }catch (Exception e){
                                                        System.out.println("Error: Either invalid Camp ID or camp is already full!");
                                                    }
                                                    break;
                                                }
                                                case "5": {
                                                    System.out.println("Please input camp ID: ");
                                                    int desiredCamp = 0;
                                                    try {
                                                        desiredCamp = Integer.parseInt(campScanner.nextLine());
                                                        if (!((Student) loggedInAccount).getIsCommittee()){
                                                            ArrayList<Camp> campCollection = CampListBrain.getCamp(desiredCamp);
                                                            Camp targetCamp = campCollection.get(0);
                                                            targetCamp.getStaticDetails().getRegistrations().withdrawCamp(loggedInAccount.getUserID());
                                                            targetCamp.getDynamicDetails().incMemberNum();
                                                            ((Student) loggedInAccount).removeRegisteredCamp(desiredCamp);
                                                            System.out.println("Withdrawn from the camp!");
                                                        }else {
                                                            if (((Student) loggedInAccount).getCommitteeCamp() == desiredCamp)
                                                            System.out.println("Unable to withdraw from camp! You are committee member!");

                                                        }

                                                    }catch (Exception e){
                                                        System.out.println("Invalid camp ID");
                                                    }
                                                    break;

                                                }
                                                default:{
                                                    System.out.println("Invalid option");
                                                    break;
                                                }
                                            }
                                            optionPrinter.StudentCampMenuOptions();
                                            menuOption = campScanner.nextLine();
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
                                            break;
                                        }

                                        optionPrinter.enquiriesOptions();
                                        String enquiryOption = campScanner.nextLine();

                                        while (!enquiryOption.contains("6")){
                                            switch (enquiryOption){
                                                case "1": {
                                                    // Show All Enquiries
                                                    System.out.println("Showing all Enquiries for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                    desiredCamp.getStaticDetails().getEnquiries().viewAllEnquiries(loggedInAccount.getUserID());
                                                    break;
                                                }
                                                case "2": {
                                                    // Show Specific Enquiry
                                                    System.out.println("Please input Enquiry ID: ");
                                                    String enqID = campScanner.nextLine();

                                                    System.out.println("Showing all Enquiries for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                    desiredCamp.getStaticDetails().getEnquiries().viewEnquiry(loggedInAccount.getUserID(), Integer.parseInt(enqID));
                                                    break;
                                                }
                                                case "3": {
                                                    // Create Enquiry
                                                    System.out.println("Please enter your query: ");
                                                    String newQuery = campScanner.nextLine();
                                                    desiredCamp.getStaticDetails().getEnquiries().submitEnquiry(loggedInAccount.getUserID(), newQuery);
                                                    System.out.println("Edited Enquiry!");
                                                    break;
                                                }
                                                case "4": {
                                                    // Edit Enquiry
                                                    try{
                                                        System.out.println("Please input Enquiry ID: ");
                                                        String enqID = campScanner.nextLine();
                                                        desiredCamp.getStaticDetails().getEnquiries().editEnquiry(loggedInAccount.getUserID(), Integer.parseInt(enqID));
                                                        System.out.println("Edited Enquiry!");
                                                        break;
                                                    }
                                                    catch (Exception e){
                                                        System.out.println("Error: Invalid Enquiry ID");
                                                        break;
                                                    }

                                                }
                                                case "5": {
                                                    // Delete Enquiry
                                                    System.out.println("Please input Enquiry ID: ");
                                                    String enqID = campScanner.nextLine();
                                                    desiredCamp.getStaticDetails().getEnquiries().deleteEnquiry(loggedInAccount.getUserID(), Integer.parseInt(enqID));

                                                    System.out.println("Deleted Enquiry");
                                                    break;
                                                }
                                                default:{
                                                    System.out.println("Invalid option");
                                                    break;
                                                }

                                            }
                                            optionPrinter.enquiriesOptions();
                                            enquiryOption = campScanner.nextLine();
                                        }
                                        System.out.println("Returning to previous menu...");
                                        break;
                                    }
                                    case "3": {
                                        // Change Password Menu

                                        System.out.println("Please enter new password ");
                                        String newPass = campScanner.nextLine();

                                        loggedInAccount.passwordChange(newPass);
                                        break;

                                    }
                                    case "4": {
                                        // Student Logging Out
                                        loggedIn = false;
                                        break;
                                    }
                                }
                            }

                        }
                        else {
                            while (loggedIn){
                                optionPrinter.committeeLoginOptions(loggedInAccount);
                                String committeeOption = campScanner.nextLine();
                                switch(committeeOption){
                                    // Printing Student Options
                                    case "1": {
                                        // Student Menu Options
                                        optionPrinter.StudentCampMenuOptions();
                                        CampDetailsPrinter cPrinter = new CampDetailsPrinter();
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
                                                    break;
                                                }
                                                case "4": {
                                                    // Joining Camp
                                                    System.out.println("Please input camp ID: ");
                                                    int desiredCamp = 0;
                                                    try {
                                                        desiredCamp = Integer.parseInt(campScanner.nextLine());

                                                        ArrayList<Camp> campCollection = CampListBrain.getCamp(desiredCamp);
                                                        Camp targetCamp = campCollection.get(0);
                                                        if (targetCamp.getStaticDetails().getRegistrations().isBlockedMember(loggedInAccount.getUserID())){
                                                            // if the person is blocked
                                                            System.out.println("You have withdrawn from the camp!");
                                                            break;
                                                        }
                                                        targetCamp.getStaticDetails().getRegistrations().registerCamp(false, loggedInAccount.getUserID());

                                                        System.out.println("Registered for the camp!");
                                                        ((CommitteeMember) loggedInAccount).addRegisteredCamp(targetCamp.getStaticDetails().getCampID());


                                                    }catch (Exception e){
                                                        System.out.println("Error: Either invalid Camp ID or camp is already full!");
                                                    }
                                                    break;
                                                }
                                                case "5": {
                                                    System.out.println("Please input camp ID: ");
                                                    int desiredCamp = 0;
                                                    try {
                                                        desiredCamp = Integer.parseInt(campScanner.nextLine());
                                                        if (((Student) loggedInAccount).getCommitteeCamp() == desiredCamp){
                                                            System.out.println("Unable to withdraw from camp! You are committee member!");
                                                            break;
                                                        }
                                                        if (!((Student) loggedInAccount).getIsCommittee()){
                                                            ArrayList<Camp> campCollection = CampListBrain.getCamp(desiredCamp);
                                                            Camp targetCamp = campCollection.get(0);
                                                            targetCamp.getStaticDetails().getRegistrations().withdrawCamp(loggedInAccount.getUserID());
                                                            targetCamp.getDynamicDetails().incMemberNum();
                                                            ((Student) loggedInAccount).removeRegisteredCamp(desiredCamp);
                                                            System.out.println("Withdrawn from the camp!");
                                                        }

                                                    }catch (Exception e){
                                                        System.out.println("Invalid camp ID");
                                                    }
                                                    break;

                                                }
                                                default:{
                                                    System.out.println("Invalid option");
                                                    break;
                                                }
                                            }
                                            optionPrinter.StudentCampMenuOptions();
                                            menuOption = campScanner.nextLine();
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
                                            break;
                                        }

                                        optionPrinter.enquiriesCommitteeOptions();
                                        String enquiryOption = campScanner.nextLine();

                                        while (!enquiryOption.contains("5")){
                                            switch (enquiryOption){
                                                case "1": {
                                                    // Show All Enquiries
                                                    System.out.println("Showing all Enquiries for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                    desiredCamp.getStaticDetails().getEnquiries().viewEnquiriesRecords();
                                                    break;
                                                }
                                                case "2": {
                                                    // Show Specific Enquiry
                                                    System.out.println("Please input Enquiry ID: ");
                                                    String enqID = campScanner.nextLine();

                                                    System.out.println("Showing all Enquiries for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                    desiredCamp.getStaticDetails().getEnquiries().viewEnquiryByID(Integer.parseInt(enqID));
                                                    break;
                                                }
                                                case "3": {
                                                    // Create Reply
                                                    System.out.println("Please input Enquiry ID: ");
                                                    String enqID = campScanner.nextLine();
                                                    System.out.println("Please enter reply: ");
                                                    String newReply = campScanner.nextLine();
                                                    desiredCamp.getStaticDetails().getEnquiries().submitReply(loggedInAccount.getUserID(), Integer.parseInt(enqID), newReply);
                                                    ((Student) loggedInAccount).addPoints(1);
                                                    break;
                                                }
                                                case "4": {
                                                    // Edit Reply
                                                    System.out.println("Please input Enquiry ID: ");
                                                    String enqID = campScanner.nextLine();
                                                    desiredCamp.getStaticDetails().getEnquiries().editReply(loggedInAccount.getUserID(), Integer.parseInt(enqID));
                                                    break;
                                                }
                                                default:{
                                                    System.out.println("Invalid option");
                                                    break;
                                                }

                                            }
                                            optionPrinter.enquiriesCommitteeOptions();
                                            enquiryOption = campScanner.nextLine();
                                        }
                                        System.out.println("Returning to previous menu...");
                                        break;
                                    }
                                    case "3": {
                                        // Change Password Menu

                                        System.out.println("Please enter new password ");
                                        String newPass = campScanner.nextLine();

                                        loggedInAccount.passwordChange(newPass);
                                        break;

                                    }
                                    case "4": {
                                        // Suggestions Menu
                                        Camp desiredCamp = null;
                                        try {
                                            ArrayList<Camp> campCollection = CampListBrain.getCamp(((Student) loggedInAccount).getCommitteeCamp());
                                            desiredCamp = campCollection.get(0);

                                        }catch (Exception e){
                                            System.out.println("Error: " + e);
                                            System.out.println("Error: You are not an Committee Member");

                                            break;
                                        }

                                        optionPrinter.suggestionOptions();
                                        String suggestionOption = campScanner.nextLine();
                                        while (!suggestionOption.contains("6")){
                                            switch (suggestionOption){
                                                case "1": {
                                                    // Show All Suggestions
                                                    System.out.println("Showing all Suggestions for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                    desiredCamp.getStaticDetails().getSuggestions().viewSuggestions(loggedInAccount.getUserID());
                                                    break;
                                                }
                                                case "2": {
                                                    // Show Specific Suggestions
                                                    try {
                                                        System.out.println("Please enter Suggestion ID: ");
                                                        String suggestionID = campScanner.nextLine();

//                                                        System.out.println("Showing all Suggestions for Camp " + desiredCamp.getStaticDetails().getCampID() + " for Student ID: " + loggedInAccount.getUserID());
                                                        desiredCamp.getStaticDetails().getSuggestions().viewSpecificSuggestion(loggedInAccount.getUserID(), Integer.parseInt(suggestionID));
                                                        break;
                                                    }catch (Exception e){
                                                        System.out.println("Error: Invalid Suggestion ID! ");
                                                        break;
                                                    }
                                                }
                                                case "3": {
                                                    // Create Suggestions
                                                    System.out.println("Please enter suggestion: ");
                                                    String newSuggestion = campScanner.nextLine();


                                                    desiredCamp.getStaticDetails().getSuggestions().submitSuggestion(loggedInAccount.getUserID(), newSuggestion);
                                                    System.out.println("Submitted a suggestion! ");
                                                    ((Student) loggedInAccount).addPoints(1);
                                                    break;
                                                }
                                                case "4": {
                                                    // Edit Suggestions
                                                    System.out.println("Please enter suggestion ID: ");
                                                    String suggID = campScanner.nextLine();

                                                    try {
                                                        desiredCamp.getStaticDetails().getSuggestions().editSuggestions(loggedInAccount.getUserID(), Integer.parseInt(suggID));
                                                        System.out.println("Submitted a suggestion! ");
                                                        break;
                                                    }catch (Exception e){
                                                        System.out.println("Error: Invalid Suggestion ID! ");
                                                        break;
                                                    }
                                                }
                                                case "5": {
                                                    // Delete Suggestions
                                                    try {


                                                        System.out.println("Please enter suggestion ID: ");
                                                        String suggID = campScanner.nextLine();


                                                        desiredCamp.getStaticDetails().getSuggestions().deleteSuggestion(loggedInAccount.getUserID(), Integer.parseInt(suggID));
//                                                        System.out.println("Submitted a suggestion! ");
                                                        ((Student) loggedInAccount).addPoints(1);
                                                        break;
                                                    }catch (Exception e){

                                                        System.out.println("Error: Invalid Suggestion ID! ");
                                                        break;
                                                    }
                                                }
                                                default:{
                                                    System.out.println("Invalid option");
                                                    break;
                                                }

                                            }
                                            optionPrinter.suggestionOptions();
                                            suggestionOption = campScanner.nextLine();
                                        }
                                        System.out.println("Returning to previous menu...");
                                        break;
                                    }
                                    case "5": {
                                        // Report Generation
                                        Camp desiredCamp = null;
                                        try {
                                            ArrayList<Camp> campCollection = CampListBrain.getCamp(((Student) loggedInAccount).getCommitteeCamp());
                                            desiredCamp = campCollection.get(0);

                                        }catch (Exception e){
                                            System.out.println("Error: You are not an Committee Member");
                                            break;
                                        }
                                        optionPrinter.generateGenReport(desiredCamp);
                                        break;
                                    }
                                    case "6": {
                                        // Committee Logging Out
                                        loggedIn = false;
                                        break;
                                    }
                                }
                            }
                        }


                    }
                    else optionPrinter.failedLoginPrompt();
                    break;
                }
                case "3": {
                    failState = true;
                    dataController.writeStaffData();
                    dataController.writeStudentData();
                    break;
                }
                default: {

                    System.out.println("Invalid option!");

                    break;
                }
            }
        }


    }
}
