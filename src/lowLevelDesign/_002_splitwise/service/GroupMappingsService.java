package lowLevelDesign._002_splitwise.service;

import lowLevelDesign._002_splitwise.models.GroupMappings;
import lowLevelDesign._002_splitwise.models.Groups;
import lowLevelDesign._002_splitwise.models.Users;

import java.util.ArrayList;
import java.util.List;

public class GroupMappingsService {
    List<GroupMappings> groupMappingsList = new ArrayList<>();

    public List<GroupMappings> createGroupMappings(Groups group, List<Users> usersList){
        List<GroupMappings> thisMappingList = new ArrayList<>();

        for (Users thisUser : usersList) {
            GroupMappings thisMapping = new GroupMappings(groupMappingsList.size() + 1, group, thisUser);
            thisMappingList.add(thisMapping);

            groupMappingsList.add(thisMapping);
        }

        return thisMappingList;
    }

    public List<Groups> getGroupListByUser(Users user) {
        List<Groups> thisUserGroupList = new ArrayList<>();
        for (GroupMappings thisGroupMapping : groupMappingsList) {
            if(thisGroupMapping.member.equals(user))
                thisUserGroupList.add(thisGroupMapping.group);
        }
        return thisUserGroupList;
    }
}
