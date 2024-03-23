package lowLevelDesign._005_twitter.services;

import lowLevelDesign._005_twitter.models.Users;

import java.util.HashMap;
import java.util.Map;

public class UsersService {
    Map<String, Users> usersMap = new HashMap<>();


    public Users createUser(String name, String email){
        Users user = new Users(usersMap.size() + 1, name, email);
        usersMap.put(email, user);
        return user;
    }
}
