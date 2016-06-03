package planner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import planner.dao.TagDao;
import planner.dao.TaskDao;
import planner.dao.TaskTagDao;
import planner.entity.TagData;
import planner.entity.TaskData;
import planner.entity.TaskTagData;

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

    public void addTask(TaskData taskData) {
        taskData.setCreationDate(new Timestamp(new Date().getTime()));
        taskData.setOwnerId(3);
        Integer taskId = taskDao.addTask(taskData);
        if(!taskData.getTags().isEmpty()) {
            addTaskTags(taskData.getTagsAsArray(), taskId);
        }
    }

    public void updateTask(TaskData taskData) {
        taskDao.updateTask(taskData);
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

    public TaskData getTaskById(Integer id) {
        TaskData taskData = taskDao.getTaskById(id);
        taskData.setUrl();
        return taskData;
    }

    public List<TaskData> getTasksByUser(String id) {
        List<TaskData> taskDataList = taskDao.getTasksByUser(id);
        for(TaskData taskData: taskDataList) {
            taskData.setUrl();
        }
        return taskDataList;
    }

    public List<TaskData> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public List<TaskData> getTasksFromInterval(String start, String end) {
        List<TaskData> taskDataList = taskDao.getTasksFromInterval(start, end);
        for(TaskData taskData: taskDataList) {
            taskData.setUrl();
        }
        return taskDataList;
    }

    public List<TaskData> getTasksFromIntervalByUserId(String start, String end, Integer id) {
        List<TaskData> taskDataList = taskDao.getTasksFromIntervalByUserId(start, end, id);
        for(TaskData taskData: taskDataList) {
            taskData.setUrl();
        }
        return taskDataList;
    }

}
