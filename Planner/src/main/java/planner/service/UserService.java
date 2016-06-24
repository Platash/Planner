package planner.service;

/**
 * Created by Anna Platash on 4/16/16.
 */


import java.util.List;
import planner.entity.UserData;

public interface UserService {

    UserData getUserById(Integer id);

    void addUser(UserData user);

    void deleteUserById(Integer id);

    List<UserData> getAllUsers();

    Integer validateUser(UserData user);

    boolean checkUserTaskPermission(Integer userId, Integer taskId);


}

