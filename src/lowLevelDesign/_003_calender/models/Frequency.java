package lowLevelDesign._003_calender.models;

import java.util.Date;

public class Frequency {
    public Integer fId;
    public Date startDate;
    public Date endDate;
    public String frequency;

    public Frequency(Integer fId, Date startDate, Date endDate, String frequency) {
        this.fId = fId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
    }
}
