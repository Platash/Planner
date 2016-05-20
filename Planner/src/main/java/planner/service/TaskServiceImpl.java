package planner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import planner.dao.TaskDao;
import planner.entity.TaskData;
import planner.formEntity.NewTaskForm;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Anna Platash on 5/8/16.
 */


@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao dao;

    public void addTask(TaskData task) {
        task.setCreationDate(new Timestamp(new Date().getTime()));
        dao.addTask(task);
    }

    public void addTask(NewTaskForm taskForm) {
        TaskData taskData = new TaskData();
        taskData.setCreationDate(new Timestamp(new Date().getTime()));
        taskData.setName(taskForm.getName());
        taskData.setDescription(taskForm.getDescription());
        taskData.setLocation(taskForm.getLocation());
        taskData.setStartDate(Timestamp.valueOf(taskForm.getStartDate()));
        taskData.setEndDate(Timestamp.valueOf(taskForm.getEndDate()));
        taskData.setOwnerId(3);
        dao.addTask(taskData);
    }

    public void deleteTaskById(String id) {
        dao.deleteTaskById(id);
    }

    public TaskData getTaskById(String id) {
        return dao.getTaskById(id);
    }

    public List<TaskData> getTasksByUser(String id) {
        return dao.getTasksByUser(id);
    }

    public List<TaskData> getAllTasks() {
        return dao.getAllTasks();
    }

}
