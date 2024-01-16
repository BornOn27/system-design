package lowLevelDesign._002_splitwise.models;

public class GroupMappings {
    public Integer gmId;
    public Groups group;
    public Users member;

    public GroupMappings(Integer gmId, Groups group, Users member) {
        this.gmId = gmId;
        this.group = group;
        this.member = member;
    }
}
