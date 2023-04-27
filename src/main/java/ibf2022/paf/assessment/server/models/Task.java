package ibf2022.paf.assessment.server.models;

import java.time.LocalDate;

// TODO: Task 4

public class Task {
    private Integer task_id;
    private String description;
    private Integer priority; 
    private LocalDate due_date; 
    private String user_id;
    
    public Integer getTask_id() {
        return task_id;
    }
    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public LocalDate getDue_date() {
        return due_date;
    }
    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    @Override
    public String toString() {
        return "Task [task_id=" + task_id + ", description=" + description + ", priority=" + priority + ", due_date="
                + due_date + ", user_id=" + user_id + "]";
    }
    public Task(String description, Integer priority, LocalDate due_date) {
        this.description = description;
        this.priority = priority;
        this.due_date = due_date;
    } 
}
