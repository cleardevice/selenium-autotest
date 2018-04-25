package org.tester.domain;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bug bug = (Bug) o;
        return isDone == bug.isDone &&
                priority == bug.priority &&
                Objects.equals(name, bug.name) &&
                Objects.equals(notes, bug.notes) &&
                Objects.equals(due, bug.due) &&
                Objects.equals(createdAt, bug.createdAt) &&
                Objects.equals(updatedAt, bug.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isDone, name, notes, priority, due, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Bug{" +
                "isDone=" + isDone +
                ", name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", priority=" + priority +
                ", due=" + due +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
