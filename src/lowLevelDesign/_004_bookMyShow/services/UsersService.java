package lowLevelDesign._004_bookMyShow.services;


import lowLevelDesign._004_bookMyShow.models.Users;

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
