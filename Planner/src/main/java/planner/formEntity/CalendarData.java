package planner.formEntity;

import planner.entity.TaskData;

/**
 * Created by Anna Platash on 5/31/16.
 */
public class CalendarData {

    private Integer id;
    private String title;
    private boolean allDay;
    private String start;
    private String end;
    private boolean editable;
    private boolean durationEditable;
    private String color;

    public CalendarData(TaskData taskData){
        this.id = taskData.getId();
        this.title = taskData.getName();
        this.start = taskData.getStartDate().toString();
        this.end = taskData.getEndDate().toString();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }


    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isDurationEditable() {
        return durationEditable;
    }

    public void setDurationEditable(boolean durationEditable) {
        this.durationEditable = durationEditable;
    }
}
