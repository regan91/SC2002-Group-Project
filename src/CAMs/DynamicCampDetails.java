package CAMs;

import CAMs.Interfaces.IDynamicCampDetailsGetter;
import CAMs.Interfaces.IDynamicCampDetailsSetter;

import java.util.ArrayList;
import java.time.LocalDate;

public class DynamicCampDetails implements IDynamicCampDetailsGetter, IDynamicCampDetailsSetter {

    private String name;
    private ArrayList<LocalDate> dates;
    private LocalDate regClosingDate;
    private boolean isOpenToNTU;
    private String location;
    private int currentSlots = 0;
    private int totalSlots;
    private int totalCommitteeSlots = 10;
    private String description;
    private boolean visibility = true;


    public String getCampName() {
        return this.name;
    }

    public ArrayList<LocalDate> getCampDates() {
        return this.dates;
    }

    public LocalDate getClosingDate() {
        return this.regClosingDate;
    }

    public boolean getOpenStatus() {
        return this.isOpenToNTU;
    }

    public String getLocation() {
        return this.location;
    }

    public int getAvailableSlots() {
        return this.totalSlots - this.currentSlots;
    }

    public int getCurrentCommitteeNum() {
        return this.totalCommitteeSlots;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getVisibility() {
        return this.visibility;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setCampDates(LocalDate startDate, LocalDate endDate) {
        ArrayList<LocalDate> datesList = new ArrayList<>();
        while (!startDate.isAfter(endDate)){
            datesList.add(startDate);
            startDate = startDate.plusDays(1);
        }
        this.dates = datesList;
    }

    public void setClosingDate(LocalDate newDate) {
        this.regClosingDate = newDate;
    }

    public void setOpenStatus(boolean state) {
        this.isOpenToNTU = state;
    }

    public void setLocation(String newLocation) {
        this.location = newLocation;
    }

    public void setMaxMember(int maxNum){
        this.totalSlots = maxNum;
    }

    public void decMemberNum() {
        this.totalSlots -= 1;
    }
    public void incMemberNum() {
        this.totalSlots += 1;
    }
    public void decCurrentCommitteNum() {
        this.totalCommitteeSlots -= 1;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setVisibility(boolean isVisible) {
        this.visibility = isVisible;
    }
}
