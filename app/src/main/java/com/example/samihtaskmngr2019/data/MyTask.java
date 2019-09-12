package com.example.samihtaskmngr2019.data;

import java.util.Date;

public class MyTask
{
    private String key;// key: unique id for each object. have to be....
    private String title;
    private String subject;
    private int important;

    public MyTask() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", important=" + important +
                '}';
    }
}
