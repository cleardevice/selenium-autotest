package org.tester.domain;

import java.util.Date;

public class BugFactory {
    public static BugFactory getInstance() {
        return new BugFactory();
    }

    public Bug get(boolean isDone, String name, String notes, int priority, Date due) {
        Bug b = new Bug();
        b.setIsDone(isDone);
        b.setName(name);
        b.setNotes(notes);
        b.setPriority(priority);
        b.setDue(due);

        return b;
    }

    public Bug getNumered(int num) {
        String numStr = Integer.toString(num);
        return get(true, "name" + numStr, "notes" + numStr, num, new Date());
    }
}
