package CAMs.Interfaces;

import CAMs.DynamicCampDetails;
import CAMs.StaticCampDetails;

public interface ICamp {
    StaticCampDetails getStaticDetails();
    DynamicCampDetails getDynamicDetails();
}
