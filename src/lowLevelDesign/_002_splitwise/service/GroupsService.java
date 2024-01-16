package lowLevelDesign._002_splitwise.service;

import lowLevelDesign._002_splitwise.models.Groups;
import lowLevelDesign._002_splitwise.models.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupsService {
    List<Groups> groupList = new ArrayList<>();
    GroupMappingsService groupMappingsService;

    public GroupsService(GroupMappingsService groupMappingsService) {
        this.groupMappingsService = groupMappingsService;
    }

    public Groups createGroup(String name, Users createdBy, List<Users> usersList){
        Groups thisGroup = new Groups(groupList.size() + 1, name, createdBy, new Date());
        groupList.add(thisGroup);

        groupMappingsService.createGroupMappings(thisGroup, usersList);


        return thisGroup;
    }

}
