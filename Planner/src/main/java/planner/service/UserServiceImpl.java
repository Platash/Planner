package planner.service;

/**
 * Created by Anna Platash on 4/16/16.
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import planner.dao.UserDao;
import planner.entity.UserData;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public UserData getUserById(String id) {
        return dao.getUserById(id);
    }

    public void saveUser(UserData user) {
        dao.saveUser(user);
    }


    public void deleteUserById(String id) {
        dao.deleteUserById(id);
    }

    public List<UserData> getAllUsers() {
        return dao.getAllUsers();
    }

}

