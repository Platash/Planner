package planner.service;

/**
 * Created by Anna Platash on 4/16/16.
 */


import java.util.List;
import planner.entity.User;

public interface UserService {

    User getUserById(String id);

    void saveUser(User user);

    void deleteUserById(String id);

    List<User> getAllUsers();


}

