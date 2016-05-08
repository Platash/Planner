package planner.entity;

import javax.persistence.*;

/**
 * Created by Anna Platash on 5/8/16.
 */
@Entity
@Table(name = "task_tag", schema = "public", catalog = "planner")
@IdClass(TaskTagDataPK.class)
public class TaskTagData {
    private Integer tagId;
    private Integer taskId;

    @Id
    @Column(name = "tag_id", nullable = false)
    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Id
    @Column(name = "task_id", nullable = false)
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

        TaskTagData that = (TaskTagData) o;

        if (tagId != null ? !tagId.equals(that.tagId) : that.tagId != null) return false;
        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagId != null ? tagId.hashCode() : 0;
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        return result;
    }
}
