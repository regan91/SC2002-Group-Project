package CAMs.Interfaces;

import java.util.ArrayList;
import java.time.LocalDate;

public interface IDynamicCampDetailsSetter {
    void setName(String newName);

    void setCampDates(LocalDate startDate, LocalDate endDate);

    void setClosingDate(LocalDate newDate);

    void setOpenStatus(boolean state);

    void setLocation(String newLocation);
    void decMemberNum();
    void decCurrentCommitteNum();
    void setDescription(String newDescription);
    void setVisibility(boolean isVisible);
}
