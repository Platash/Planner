package planner.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Anna Platash on 5/3/16.
 */
@Entity
@Table(name = "task", schema = "public", catalog = "planner")
public class TaskData {
    private Integer id;
    private Timestamp creationDate;
    private Timestamp modificationDate;
    private Timestamp startDate;
    private Timestamp endDate;
    private String location;
    private String description;
    private Integer ownerId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id")
    @SequenceGenerator(name = "task_id", sequenceName = "task_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "creation_date", nullable = false)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "modification_date", nullable = true)
    public Timestamp getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Timestamp modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Basic
    @Column(name = "start_date", nullable = true)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "location", nullable = true, length = 100)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "owner_id", nullable = false)
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskData taskData = (TaskData) o;

        if (id != null ? !id.equals(taskData.id) : taskData.id != null) return false;
        if (creationDate != null ? !creationDate.equals(taskData.creationDate) : taskData.creationDate != null)
            return false;
        if (modificationDate != null ? !modificationDate.equals(taskData.modificationDate) : taskData.modificationDate != null)
            return false;
        if (startDate != null ? !startDate.equals(taskData.startDate) : taskData.startDate != null) return false;
        if (endDate != null ? !endDate.equals(taskData.endDate) : taskData.endDate != null) return false;
        if (location != null ? !location.equals(taskData.location) : taskData.location != null) return false;
        if (description != null ? !description.equals(taskData.description) : taskData.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

}
