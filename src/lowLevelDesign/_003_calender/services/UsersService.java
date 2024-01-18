package lowLevelDesign._003_calender.services;

import lowLevelDesign._003_calender.models.Users;

import java.util.ArrayList;
import java.util.List;

public class UsersService {
    List<Users> usersList = new ArrayList<>();

    public Users createUser(String name, String email){
        Users thisUser = new Users(usersList.size() + 1, name, email);
        usersList.add(thisUser);
        return thisUser;
    }
}
