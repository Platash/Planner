package planner.service;

/**
 * Created by Anna Platash on 4/16/16.
 */


import java.util.List;
import planner.entity.UserData;

public interface UserService {

    UserData getUserById(String id);

    void addUser(UserData user);

    void deleteUserById(String id);

    List<UserData> getAllUsers();


}

