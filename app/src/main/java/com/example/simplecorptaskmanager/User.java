package com.example.simplecorptaskmanager;

public class User {

    String name;
    String jobTitle;

    public User(String name, String jobTitle) {
        this.name = name;
        this.jobTitle = jobTitle;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }
}
