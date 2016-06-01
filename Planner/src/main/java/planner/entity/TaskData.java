package planner.entity;

import planner.config.Params;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Anna Platash on 5/3/16.
 */
@Entity
@Table(name = "task", schema = "public", catalog = "planner")
public class TaskData {
    private Integer id;
    private String title;
    private Timestamp creationDate;
    private Timestamp modificationDate;
    private Timestamp start;
    private Timestamp end;
    private String location;
    private String description;
    private Integer ownerId;

    @Transient
    private boolean allDay;
    @Transient
    private boolean editable;
    @Transient
    private boolean durationEditable;
    @Transient
    private String color;
    @Transient
    private String url;
    @Transient
    private String tags;


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
    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp startDate) {
        this.start = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp endDate) {
        this.end = endDate;
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

    @Basic
    @Column(name = "title", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
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
        if (start != null ? !start.equals(taskData.start) : taskData.start != null) return false;
        if (end != null ? !end.equals(taskData.end) : taskData.end != null) return false;
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
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }


    @Transient
    public boolean isAllDay() {
        return allDay;
    }

    @Transient
    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    @Transient
    public boolean isEditable() {
        return editable;
    }

    @Transient
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Transient
    public boolean isDurationEditable() {
        return durationEditable;
    }

    @Transient
    public void setDurationEditable(boolean durationEditable) {
        this.durationEditable = durationEditable;
    }

    @Transient
    public String getColor() {
        return color;
    }

    @Transient
    public void setColor(String color) {
        this.color = color;
    }

    @Transient
    public String getUrl() {
        return url;
    }

    @Transient
    public void setUrl() {
        this.url = Params.TASK_URL + this.id;
    }

    @Transient
    public void setUrl(String url) {
        this.url = url;
    }

    @Transient
    public String getTags() {
        return tags;
    }

    @Transient
    public void setTags(String tags) {
        this.tags = tags;
    }

    @Transient
    public String[] getTagsAsArray() {
        return tags.split(Params.SEPARATOR);
    }
}
