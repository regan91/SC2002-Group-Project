package HelperPackages;

import CAMs.Camp;

public class CampPrinter {
    public void printCampBasicDetails(Camp campRecord){
        System.out.println("Camp ID: " + campRecord.getStaticDetails().getCampID());
        System.out.println("Camp In Charge: " + campRecord.getStaticDetails().getStaffIC());
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
        System.out.println("Camp In Charge: " + campRecord.getStaticDetails().getStaffIC());
        System.out.println("Camp Name: " + campRecord.getDynamicDetails().getCampName());
        System.out.println("Camp Registration Close: " + campRecord.getDynamicDetails().getClosingDate());
        System.out.println("Camp Description: " + campRecord.getDynamicDetails().getDescription());
        System.out.println("Camp Slots Left: " + campRecord.getDynamicDetails().getAvailableSlots());
        System.out.println("Camp Location: " + campRecord.getDynamicDetails().getLocation());
        System.out.println("----");
        System.out.println();
    }
}
