package planner.entity;

import javax.persistence.*;

/**
 * Created by Anna Platash on 5/8/16.
 */
@Entity
@Table(name = "invitation", schema = "public", catalog = "planner")
@IdClass(InvitationPK.class)
public class Invitation {
    private Integer taskId;
    private Integer userId;
    private String status;

    @Id
    @Column(name = "task_id", nullable = false)
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invitation that = (Invitation) o;

        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskId != null ? taskId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "status", nullable = false, length = -1)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
