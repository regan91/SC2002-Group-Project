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
                        Staff staffUser = (Staff) loggedInAccount;
                        while (loggedIn){
                            optionPrinter.StaffLoginOptions(staffUser);
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
                                                ArrayList<Camp> campCollection = CampListBrain.getCreatedCamps(staffUser.getUserID());
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
                                                while (!editOptions.contains("9")) {
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
                                                            if (desiredCamp.getDynamicDetails().getOccupiedSlots() <= Integer.parseInt(changeValue)){
                                                                desiredCamp.getDynamicDetails().setMaxMember(Integer.parseInt(changeValue));

                                                                System.out.println("Camp Max Participants has been updated!");
                                                            }else {
                                                                System.out.println("Not possible to update Camp Participants! New limit is greater than current participants!");

                                                            }

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
                                                        case "8": {
                                                            // Edit Description
                                                            System.out.println("Please set Camp description: ");
                                                            String changeValue = campScanner.nextLine();
                                                            desiredCamp.getDynamicDetails().setDescription(changeValue);

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
                                                desiredCamp.getStaticDetails().getEnquiries().submitReply(staffUser.getUserID(),Integer.parseInt(enqID), reply);
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
                                                // Show All suggestions
                                                System.out.println("Showing all Suggestions for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                desiredCamp.getStaticDetails().getSuggestions().viewAllSuggestionsRecords();
                                                break;
                                            }
                                            case "2": {
                                                // show specific suggestion
                                                System.out.println("Please input student ID: ");
                                                String studentID = campScanner.nextLine();

                                                System.out.println("Showing all Suggestions for Camp " + desiredCamp.getStaticDetails().getCampID() + " for Student ID: " + studentID);
                                                desiredCamp.getStaticDetails().getSuggestions().viewSuggestions(studentID);
                                                break;
                                            }
                                            case "3": {
                                                // approve suggestion
                                                System.out.println("Please input Suggestion ID: ");
                                                String sugID = campScanner.nextLine();
                                                String desiredStudentID = desiredCamp.getStaticDetails().getSuggestions().getStudentID(Integer.parseInt(sugID));
                                                CommitteeMember desiredMember = (CommitteeMember) users.getStudent(desiredStudentID);
                                                desiredCamp.getStaticDetails().getSuggestions().approveSuggestion(desiredMember ,Integer.parseInt(sugID));
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

                                        break;
                                    }
                                    optionPrinter.reportPrivilegedOptions();
                                    String reportOption = campScanner.nextLine();

                                    while (!reportOption.contains("4")){
                                        switch (reportOption){
                                            case "1": {
                                                System.out.println("Generating General Report for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                optionPrinter.generateGenReport(desiredCamp, users);

                                                break;
                                            }
                                            case "2": {
                                                System.out.println("Generating Performance Report for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                optionPrinter.generatePerformanceReport(desiredCamp, users);
                                                break;
                                            }
                                            case "3": {
                                                System.out.println("Generating Custome Report for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                optionPrinter.customReportOptions();
                                                String filterOption = campScanner.nextLine();

                                                while (!filterOption.contains("4")) {
                                                    switch (filterOption) {
                                                        case "1": {
                                                            System.out.println("Generating Custom Report for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                            optionPrinter.generateCustomReport(desiredCamp, users,1);

                                                            break;
                                                        }
                                                        case "2": {
                                                            System.out.println("Generating Custom Report for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                            optionPrinter.generateCustomReport(desiredCamp, users,2);
                                                            break;
                                                        }
                                                        case "3": {
                                                            System.out.println("Generating Custom Report for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                            optionPrinter.generateCustomReport(desiredCamp, users,3);
                                                            break;
                                                        }
                                                        default: {
                                                            System.out.println("Invalid option");
                                                            break;
                                                        }
                                                    }
                                                    optionPrinter.customReportOptions();
                                                    filterOption = campScanner.nextLine();
                                                }
                                                break;
                                            }
                                            default:{
                                                System.out.println("Invalid option");
                                                break;
                                            }
                                        }optionPrinter.reportPrivilegedOptions();
                                        reportOption = campScanner.nextLine();
                                    }
                                    System.out.println("Returning to previous menu...");
                                    break;
                                }
                                case "5": {
                                    // Change Password Menu
                                    System.out.println("Please enter new password ");
                                    String newPass = campScanner.nextLine();

                                    staffUser.passwordChange(newPass);
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
                        if (!(loggedInAccount instanceof CommitteeMember)) {
                            // NOT AN IC
                            Student studentUser = (Student) loggedInAccount;
                            while (loggedIn){
                                optionPrinter.StudentLoginOptions(studentUser);
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
                                                    ArrayList<Camp> campCollection = CampListBrain.getAllCamps(studentUser.getFaculty());
                                                    for (Camp campRec: campCollection){
                                                        cPrinter.printCampBasicDetails(campRec);
                                                    }
                                                    break;
                                                }
                                                case "2": {
                                                    // printing out joined camps
                                                    System.out.println("Showing joined camps: ");
                                                    ArrayList<Integer> retrievedCamps = studentUser.getRegisteredCamps();
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

                                                        System.out.println("Register as committee member?: [Y/N] ");
                                                        String committeeChoice = campScanner.nextLine();
                                                        if (targetCamp.getDynamicDetails().getAvailableSlots() > 0){
                                                            if (committeeChoice.contains("Y")) {
                                                                if (targetCamp.getDynamicDetails().getCurrentCommitteeNum() > 0){
                                                                    boolean registerStatus = targetCamp.getStaticDetails().getRegistrations().registerCamp(true, studentUser.getUserID());
                                                                    if (registerStatus){
                                                                        // if the person is blocked
                                                                        System.out.println("You had withdrawn from the camp!");
                                                                        break;
                                                                    }
                                                                    targetCamp.getDynamicDetails().decCurrentCommitteNum();

                                                                    Student oldState = users.getStudent(studentUser.getUserID());
                                                                    CommitteeMember newState = new CommitteeMember(oldState, targetCamp.getStaticDetails().getCampID());
                                                                    users.upgradeStudent(oldState, newState);
                                                                    newState.setInChargeCamp(desiredCamp);
                                                                    newState.addRegisteredCamp(targetCamp.getStaticDetails().getCampID());

                                                                    System.out.println("Registered for the camp as Committee Member!");
                                                                    break;
                                                                }
                                                            }else {
                                                                if (targetCamp.getDynamicDetails().getAvailableSlots() > 0){
                                                                    boolean registerStatus = targetCamp.getStaticDetails().getRegistrations().registerCamp(false, studentUser.getUserID());
                                                                    if (registerStatus){
                                                                        // if the person is blocked
                                                                        System.out.println("You had withdrawn from the camp!");
                                                                        break;
                                                                    }
                                                                targetCamp.getDynamicDetails().incMemberNum();
                                                                System.out.println("Registered for the camp as participant!");}
                                                                studentUser.addRegisteredCamp(targetCamp.getStaticDetails().getCampID());
                                                                break;
                                                            }
                                                        }
                                                        else{
                                                            System.out.println("Camp is full!");
                                                        }

                                                    }catch (Exception e){
                                                        System.out.println("Error: Either invalid Camp ID or camp is already full!");
                                                    }
                                                    break;
                                                }
                                                case "5": {
                                                    // leaving camp
                                                    System.out.println("Please input camp ID: ");
                                                    int desiredCamp = 0;
                                                    try {
                                                        desiredCamp = Integer.parseInt(campScanner.nextLine());
                                                        ArrayList<Camp> campCollection = CampListBrain.getCamp(desiredCamp);
                                                        Camp targetCamp = campCollection.get(0);
                                                        targetCamp.getStaticDetails().getRegistrations().withdrawCamp(studentUser.getUserID());
                                                        targetCamp.getDynamicDetails().decMemberNum();
                                                        studentUser.removeRegisteredCamp(desiredCamp);
                                                        System.out.println("Withdrawn from the camp!");

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
                                                    desiredCamp.getStaticDetails().getEnquiries().viewAllEnquiries(studentUser.getUserID());
                                                    break;
                                                }
                                                case "2": {
                                                    // Show Specific Enquiry
                                                    System.out.println("Please input Enquiry ID: ");
                                                    String enqID = campScanner.nextLine();

                                                    System.out.println("Showing all Enquiries for Camp " + desiredCamp.getStaticDetails().getCampID());
                                                    desiredCamp.getStaticDetails().getEnquiries().viewEnquiry(studentUser.getUserID(), Integer.parseInt(enqID));
                                                    break;
                                                }
                                                case "3": {
                                                    // Create Enquiry
                                                    System.out.println("Please enter your query: ");
                                                    String newQuery = campScanner.nextLine();
                                                    desiredCamp.getStaticDetails().getEnquiries().submitEnquiry(studentUser.getUserID(), newQuery);
                                                    System.out.println("Edited Enquiry!");
                                                    break;
                                                }
                                                case "4": {
                                                    // Edit Enquiry
                                                    try{
                                                        System.out.println("Please input Enquiry ID: ");
                                                        String enqID = campScanner.nextLine();
                                                        desiredCamp.getStaticDetails().getEnquiries().editEnquiry(studentUser.getUserID(), Integer.parseInt(enqID));
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
                                                    desiredCamp.getStaticDetails().getEnquiries().deleteEnquiry(studentUser.getUserID(), Integer.parseInt(enqID));

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

                                        studentUser.passwordChange(newPass);
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
                            CommitteeMember committeeUser = (CommitteeMember) loggedInAccount;
                            while (loggedIn){
                                optionPrinter.committeeLoginOptions(committeeUser);
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
                                                    ArrayList<Camp> campCollection = CampListBrain.getAllCamps(committeeUser.getFaculty());
                                                    for (Camp campRec: campCollection){
                                                        cPrinter.printCampBasicDetails(campRec);
                                                    }
                                                    break;
                                                }
                                                case "2": {
                                                    // printing out joined camps
                                                    System.out.println("Showing joined camps: ");
                                                    ArrayList<Integer> retrievedCamps = committeeUser.getRegisteredCamps();
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
                                                        if (targetCamp.getDynamicDetails().getAvailableSlots() > 0) {

                                                            boolean registerStatus = targetCamp.getStaticDetails().getRegistrations().registerCamp(false, committeeUser.getUserID());
                                                            if (registerStatus){
                                                                // if the person is blocked
                                                                System.out.println("You had withdrawn from the camp!");
                                                                break;
                                                            }

                                                            System.out.println("Registered for the camp!");
                                                            committeeUser.addRegisteredCamp(targetCamp.getStaticDetails().getCampID());

                                                        }else {
                                                            System.out.println("Camp is full!");
                                                            committeeUser.addRegisteredCamp(targetCamp.getStaticDetails().getCampID());


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
                                                        if (committeeUser.getInChargeCamp() == desiredCamp){
                                                            System.out.println("Unable to withdraw from camp! You are committee member!");
                                                            break;
                                                        }else {
                                                            ArrayList<Camp> campCollection = CampListBrain.getCamp(desiredCamp);
                                                            Camp targetCamp = campCollection.get(0);
                                                            targetCamp.getStaticDetails().getRegistrations().withdrawCamp(committeeUser.getUserID());
                                                            targetCamp.getDynamicDetails().incMemberNum();
                                                            committeeUser.removeRegisteredCamp(desiredCamp);
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
                                                    desiredCamp.getStaticDetails().getEnquiries().submitReply(committeeUser.getUserID(), Integer.parseInt(enqID), newReply);
                                                    committeeUser.addPoints(1);
                                                    break;
                                                }
                                                case "4": {
                                                    // Edit Reply
                                                    System.out.println("Please input Enquiry ID: ");
                                                    String enqID = campScanner.nextLine();
                                                    desiredCamp.getStaticDetails().getEnquiries().editReply(committeeUser.getUserID(), Integer.parseInt(enqID));
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

                                        committeeUser.passwordChange(newPass);
                                        break;

                                    }
                                    case "4": {
                                        // Suggestions Menu
                                        Camp desiredCamp = null;
                                        try {
                                            ArrayList<Camp> campCollection = CampListBrain.getCamp(committeeUser.getInChargeCamp());
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
                                                    desiredCamp.getStaticDetails().getSuggestions().viewSuggestions(committeeUser.getUserID());
                                                    break;
                                                }
                                                case "2": {
                                                    // Show Specific Suggestions
                                                    try {
                                                        System.out.println("Please enter Suggestion ID: ");
                                                        String suggestionID = campScanner.nextLine();

//                                                        System.out.println("Showing all Suggestions for Camp " + desiredCamp.getStaticDetails().getCampID() + " for Student ID: " + loggedInAccount.getUserID());
                                                        desiredCamp.getStaticDetails().getSuggestions().viewSpecificSuggestion(committeeUser.getUserID(), Integer.parseInt(suggestionID));
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


                                                    desiredCamp.getStaticDetails().getSuggestions().submitSuggestion(committeeUser.getUserID(), newSuggestion);
                                                    System.out.println("Submitted a suggestion! ");
                                                    committeeUser.addPoints(1);
                                                    break;
                                                }
                                                case "4": {
                                                    // Edit Suggestions
                                                    System.out.println("Please enter suggestion ID: ");
                                                    String suggID = campScanner.nextLine();

                                                    try {
                                                        desiredCamp.getStaticDetails().getSuggestions().editSuggestions(committeeUser.getUserID(), Integer.parseInt(suggID));
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


                                                        desiredCamp.getStaticDetails().getSuggestions().deleteSuggestion(committeeUser.getUserID(), Integer.parseInt(suggID));
//                                                        System.out.println("Submitted a suggestion! ");
                                                        committeeUser.addPoints(1);
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
                                            ArrayList<Camp> campCollection = CampListBrain.getCamp((committeeUser.getInChargeCamp()));
                                            desiredCamp = campCollection.get(0);

                                        }catch (Exception e){
                                            System.out.println("Error: You are not an Committee Member");
                                            break;
                                        }
                                        optionPrinter.generateGenReport(desiredCamp, users);
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
