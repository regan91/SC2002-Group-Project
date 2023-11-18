package CAMs.Interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IDynamicCampDetailsGetter {
    String getCampName();
    ArrayList<LocalDate> getCampDates();
    LocalDate getClosingDate();
    boolean getOpenStatus();
    String getLocation();
    int getAvailableSlots();
    int getCurrentCommitteeNum();
    String getDescription();
    boolean getVisibility();

}
