package HelperPackages.PrinterOperations;

import HelperPackages.PrinterOperations.Interfaces.ILoginPrinter;

// template to copy:
//      System.out.println();
public class LoginPrinter implements ILoginPrinter {
      public void idPrompt() {
        System.out.println("Welcome to CAMs!");
        System.out.println("Please enter your UserID");

    }

    public void passwordPrompt() {
        System.out.println("Please enter your password");

    }
    public void failedLoginPrompt() {
        System.out.println("Invalid UserID or password");
        System.out.println("Please try again");

    }

    public void successStaffLogin() {
        System.out.println("Login successful");
        System.out.println();
        System.out.println("Staff Options:");
        System.out.println("1. Camp Menu");
        System.out.println("2. Enquiries Menu");
        System.out.println("3. Suggestions Menu");
        System.out.println("4. Report Menu");
    }
    public void successStudentLogin(boolean isCommittee) {
        System.out.println("Login successful");
        System.out.println();
        System.out.println("Student Options:");
        System.out.println("1. Camp Menu");
        System.out.println("2. Enquiries Menu");
        System.out.println("3. Suggestions Menu");
        System.out.println("4. Report Menu");

        if (isCommittee) {
            System.out.println("Committee Options:");
            System.out.println("Committee Options:");
            System.out.println("1. Camp Menu");
            System.out.println("2. Enquries Menu");
            System.out.println("3. Suggestions Menu");
            System.out.println("4. Report Menu");
        }
    }
}
