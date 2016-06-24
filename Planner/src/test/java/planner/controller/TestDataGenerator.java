package planner.controller;

import planner.entity.TaskData;
import planner.entity.UserData;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Anna Platash on 6/24/16.
 */
public class TestDataGenerator {

    public UserData generateUser1() {
        UserData userData = new UserData();
        userData.setId(1);
        userData.setCreationDate(new Timestamp(new Date().getTime()));
        userData.setLogin("login");
        userData.setPassword("password");
        userData.setName("Ania");
        return userData;
    }

    public UserData generateUser2() {
        UserData userData = new UserData();
        userData.setId(2);
        userData.setCreationDate(new Timestamp(new Date().getTime()));
        userData.setLogin("login2");
        userData.setPassword("password2");
        userData.setName("Ania2");
        return userData;
    }

    public TaskData generateTask1() {
        TaskData taskData = new TaskData();
        taskData.setId(1);
        taskData.setLocation("UJ");
        taskData.setCreationDate(new Timestamp(new Date().getTime()));
        taskData.setStart(new Timestamp(new Date().getTime()));
        taskData.setEnd(new Timestamp(new Date().getTime()));
        taskData.setDescription("test description");
        taskData.setTitle("test title");
        return taskData;
    }

    public TaskData generateTask2() {
        TaskData taskData = new TaskData();
        taskData.setId(2);
        taskData.setLocation("UJ2");
        taskData.setCreationDate(new Timestamp(new Date().getTime()));
        taskData.setStart(new Timestamp(new Date().getTime()));
        taskData.setEnd(new Timestamp(new Date().getTime()));
        taskData.setDescription("test description 2");
        taskData.setTitle("test title 2");
        return taskData;
    }
}
