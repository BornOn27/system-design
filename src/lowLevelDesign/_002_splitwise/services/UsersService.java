package lowLevelDesign._002_splitwise.services;

import lowLevelDesign._002_splitwise.models.Users;

import java.util.ArrayList;
import java.util.List;

public class UsersService {
    List<Users> usersList = new ArrayList<>();

    public Users createUser(String name, String email){
        Users user = new Users(usersList.size() + 1, name, email);
        return user;
    }
}
