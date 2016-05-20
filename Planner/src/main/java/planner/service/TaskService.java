package planner.service;

import planner.entity.TaskData;
import planner.formEntity.NewTaskForm;

import java.util.List;

/**
 * Created by Anna Platash on 5/8/16.
 */
public interface TaskService {
    void addTask(TaskData task);

    void addTask(NewTaskForm taskForm);

    void deleteTaskById(String id);

    TaskData getTaskById(String id);

    List<TaskData> getTasksByUser(String id);

    List<TaskData> getAllTasks();
}
