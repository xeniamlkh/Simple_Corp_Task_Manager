package com.example.simplecorptaskmanager;

import java.util.Comparator;

public class SortByName implements Comparator<User> {
    @Override
    public int compare(User userA, User userB) {
        return userA.getName().compareTo(userB.getName());
    }
}
