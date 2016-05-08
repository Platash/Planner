package planner.service;

import planner.entity.TaskData;
import java.util.List;

/**
 * Created by Anna Platash on 5/8/16.
 */
public interface TaskService {
    void addTask(TaskData task);

    void deleteTaskById(String id);

    TaskData getTaskById(String id);

    List<TaskData> getTasksByUser(String id);

    List<TaskData> getAllTasks();
}
