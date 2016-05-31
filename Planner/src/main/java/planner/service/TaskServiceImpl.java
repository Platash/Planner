package planner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import planner.dao.TagDao;
import planner.dao.TaskDao;
import planner.dao.TaskTagDao;
import planner.entity.TagData;
import planner.entity.TaskData;
import planner.entity.TaskTagData;
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
    private TaskDao taskDao;

    @Autowired
    private TaskTagDao taskTagDao;

    @Autowired
    private TagDao tagDao;

    public void addTask(TaskData task) {
        task.setCreationDate(new Timestamp(new Date().getTime()));
        Integer taskId = taskDao.addTask(task);
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
        Integer taskId = taskDao.addTask(taskData);
        addTaskTags(taskForm.getTagsAsArray(), taskId);


    }

    private void addTaskTags(String[] tags, Integer taskId) {
        for(int i = 0; i < tags.length; i++) {
            String tag = tags[i];
            if(tagDao.getTagByName(tag) == null) {
                TagData tagData = new TagData();
                tagData.setName(tag);
                tagDao.addTag(tagData);
            }
            TaskTagData taskTagData = new TaskTagData();
            taskTagData.setTagName(tag);
            taskTagData.setTaskId(taskId);
            taskTagDao.addTaskTag(taskTagData);
        }
    }

    public void deleteTaskById(String id) {
        taskDao.deleteTaskById(id);
    }

    public TaskData getTaskById(String id) {
        return taskDao.getTaskById(id);
    }

    public List<TaskData> getTasksByUser(String id) {
        return taskDao.getTasksByUser(id);
    }

    public List<TaskData> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public List<TaskData> getTasksFromInterval(String start, String end) {
        return taskDao.getTasksFromInterval(start, end);
    }

}
