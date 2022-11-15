package org.tester.domain;

import lombok.*;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.Objects;

@Data
public class Bug {
    private boolean isDone;
    private String name;
    private String notes;
    private int priority;
    private Date due;

    private Date createdAt;
    private Date updatedAt;

    public String getPriorityString() {
        return Integer.toString(priority);
    }

    public String getDueString() {
        return DateFormatUtils.format(due, "yyyy-MM-dd");
    }
}
