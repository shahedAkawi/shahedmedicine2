package com.example.samihtaskmngr2019.data;

import java.util.Date;

public class MyTask
{
    private String key;// key: unique id for each object. have to be....
    private String title;
    private String subject;
    private String owner;
    private int important;
    private String image;

    public MyTask() {
        //kkjhkjhkjhkjhkj
    }

    public String getKey() {
        return key;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
