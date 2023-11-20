package HelperPackages.PrinterOperations;

import CAMs.Camp;

import java.time.LocalDate;

public class CampDetailsPrinter {
    public void printCampBasicDetails(Camp campRecord){
        System.out.println("Camp ID: " + campRecord.getStaticDetails().getCampID());
        System.out.println("Camp In Charge: " + campRecord.getStaticDetails().getStaffIC().getUserID());
        System.out.println("Camp Name: " + campRecord.getDynamicDetails().getCampName());
        System.out.println("Camp Registration Close: " + campRecord.getDynamicDetails().getClosingDate());
        System.out.println("Camp Description: " + campRecord.getDynamicDetails().getDescription());
        System.out.println("Camp Slots Left: " + campRecord.getDynamicDetails().getAvailableSlots());
        System.out.println("Camp Location: " + campRecord.getDynamicDetails().getLocation());
        System.out.println("----");
        System.out.println();
    }

    public void printCreatedCamp(Camp campRecord){
        System.out.println("Camp ID: " + campRecord.getStaticDetails().getCampID());
        System.out.println("Camp In Charge: " + campRecord.getStaticDetails().getStaffIC().getUserID());
        System.out.println("Camp Name: " + campRecord.getDynamicDetails().getCampName());
        System.out.println("Camp Registration Close: " + campRecord.getDynamicDetails().getClosingDate());
        System.out.println("Camp Description: " + campRecord.getDynamicDetails().getDescription());
        System.out.println("Camp Slots Left: " + campRecord.getDynamicDetails().getAvailableSlots());
        System.out.println("Camp Location: " + campRecord.getDynamicDetails().getLocation());
        System.out.println("----");
        System.out.println();
    }
    public void printDetailedCamp(Camp campRecord){
        System.out.println("Camp ID: " + campRecord.getStaticDetails().getCampID());
        System.out.println("Camp In Charge: " + campRecord.getStaticDetails().getStaffIC().getUserID());
        System.out.println("Camp Name: " + campRecord.getDynamicDetails().getCampName());
        System.out.println("Camp Dates: (YYYY-MM-DD)");
        if (campRecord.getDynamicDetails().getCampDates().isEmpty()){
            System.out.println();
        } else {
            for (LocalDate date : campRecord.getDynamicDetails().getCampDates()) {
                System.out.println(" " + date.toString());
            }
        }
        System.out.println("Camp Registration Close: " + campRecord.getDynamicDetails().getClosingDate());
        System.out.println("Camp Description: " + campRecord.getDynamicDetails().getDescription());
        System.out.println("Camp Slots Left: " + campRecord.getDynamicDetails().getAvailableSlots());
        System.out.println("Camp Location: " + campRecord.getDynamicDetails().getLocation());
        System.out.println("----");
        System.out.println();
    }
}
