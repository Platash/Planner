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

    public void updateUser(UserData currentUser, UserData userData) {
        userData.setModificationDate(new Timestamp(new Date().getTime()));
        userDao.updateUser(currentUser, userData);
    }

    public void deleteUserById(Integer id) {
        int result = userDao.deleteUserById(id);

    }

    public List<UserData> getAllUsers() {
        return userDao.getAllUsers();
    }

    public Integer validateUser(UserData user) {
        if(user.getLogin().isEmpty() || user.getLogin() == null) {
            return null;
        }
        UserData userData = userDao.validateUser(user.getLogin(), user.getPassword());
        if(userData != null) {
            return userData.getId();
        } else {
            return null;
        }

    }

    public boolean checkUserTaskPermission(Integer userId, Integer taskId){
        return taskDao.getTaskById(taskId).getOwnerId().equals(userId);
    }
}

