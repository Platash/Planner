package planner.service;

import planner.entity.TaskData;
import planner.exception.BadSQLException;

import java.util.List;

/**
 * Created by Anna Platash on 5/8/16.
 */
public interface TaskService {
    void addTask(TaskData task);

    void updateTask(TaskData task);

    void deleteTaskById(Integer id) throws BadSQLException;

    TaskData getTaskById(Integer id);

    List<TaskData> getTasksByUser(Integer id);

    List<TaskData> getAllTasks();

    List<TaskData> getTasksFromInterval(String start, String end);

    List<TaskData> getTasksFromIntervalByUserId(String start, String end, Integer id);
}
