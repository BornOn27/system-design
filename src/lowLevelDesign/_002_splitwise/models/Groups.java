package lowLevelDesign._002_splitwise.models;

import java.util.Date;

public class Groups {
    public Integer gId;
    public String name;
    public Users createdBy;
    public Date createdOn;

    public Groups(Integer gId, String name, Users createdBy, Date createdOn) {
        this.gId = gId;
        this.name = name;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }
}
