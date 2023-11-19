package CAMs.Interfaces;

import java.util.ArrayList;
import java.time.LocalDate;

public interface IDynamicCampDetailsSetter {
    void setName(String newName);

    void setCampDates(LocalDate startDate, LocalDate endDate);

    void setClosingDate(LocalDate newDate);

    void setOpenStatus(boolean state);

    void setLocation(String newLocation);
    void setMaxMember(int maxNum);
    void decMemberNum();
    void incMemberNum();
    void decCurrentCommitteNum();
    void setDescription(String newDescription);
    void setVisibility(boolean isVisible);
}
