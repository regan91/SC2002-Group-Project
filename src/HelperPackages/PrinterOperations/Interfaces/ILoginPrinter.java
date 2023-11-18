package HelperPackages.PrinterOperations.Interfaces;

public interface ILoginPrinter {
    void idPrompt();
    void passwordPrompt();
    void failedLoginPrompt();
    void successStaffLogin();
    void successStudentLogin(boolean isCommitteeMember);

}
