package org.tester.domain;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class Bug {
    private boolean isDone;
    private String name;
    private String notes;
    private int priority;
    private Date due;

    private Date createdAt;
    private Date updatedAt;

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getPriority() {
        return priority;
    }
    public String getPriorityString() {
        return Integer.toString(priority);
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDue() {
        return due;
    }
    public String getDueString() {
        return DateFormatUtils.format(due, "yyyy-MM-dd");
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
