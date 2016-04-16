package planner.service;

/**
 * Created by Anna Platash on 4/16/16.
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import planner.dao.UserDao;
import planner.entity.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public User getUserById(String id) {
        return dao.getUserById(id);
    }

    public void saveUser(User user) {
        dao.saveUser(user);
    }


    public void deleteUserById(String id) {
        dao.deleteUserById(id);
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

}

