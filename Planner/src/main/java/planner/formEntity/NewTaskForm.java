package planner.formEntity;

import java.util.ArrayList;

/**
 * Created by Anna Platash on 5/20/16.
 */
public class NewTaskForm {

    private String startDate;
    private String endDate;
    private String location;
    private String description;
    private String name;
    private String tags;
    private final String SEPARATOR = "#";

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public String[] getTagsAsArray() {
        return tags.split(SEPARATOR);
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
