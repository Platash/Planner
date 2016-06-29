package planner.service;

/**
 * Created by Anna Platash on 4/16/16.
 */

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import planner.dao.TaskDao;
import planner.dao.UserDao;
import planner.entity.UserData;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private TaskDao taskDao;

    public UserData getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    public void addUser(UserData user) {
        user.setCreationDate(new Timestamp(new Date().getTime()));
        userDao.addUser(user);
    }

    public int updateUser(UserData currentUser, UserData userData) {
        userData.setModificationDate(new Timestamp(new Date().getTime()));
        return userDao.updateUser(currentUser, userData);
    }

    public int deleteUserById(Integer id) {
        return userDao.deleteUserById(id);

    }

    public List<UserData> getAllUsers() {
        return userDao.getAllUsers();
    }

    public UserData validateUser(UserData user) {
        if(user.getLogin().isEmpty() || user.getLogin() == null) {
            return null;
        }
        return userDao.validateUser(user.getLogin(), user.getPassword());

    }

    public boolean checkUserTaskPermission(Integer userId, Integer taskId){
        return taskDao.getTaskById(taskId).getOwnerId().equals(userId);
    }
}

