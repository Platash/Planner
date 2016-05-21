package planner.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Anna Platash on 5/8/16.
 */
public class TaskTagDataPK implements Serializable {
    private String tagName;
    private Integer taskId;

    @Column(name = "tag_name", nullable = false)
    @Id
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Column(name = "task_id", nullable = false)
    @Id
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskTagDataPK that = (TaskTagDataPK) o;

        if (tagName != null ? !tagName.equals(that.tagName) : that.tagName != null) return false;
        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagName != null ? tagName.hashCode() : 0;
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        return result;
    }
}
