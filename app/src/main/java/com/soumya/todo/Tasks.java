package com.soumya.todo;

import java.util.Date;

/**
 * Created by Soumya on 10/27/2016.
 */

public class Tasks {

    private String Task;
    private Date date;

    public Tasks(String task, Date date) {
        super();
        Task = task;
        this.date = date;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        Task = task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
