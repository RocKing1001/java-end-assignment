package me.piguy.assignment.database;

import me.piguy.assignment.models.User;

import java.util.HashMap;

/**
 * An in memory key-value database which makes use of a hashmap
 * Current purpose of this database is to store users
 */
public class MemoryUserDB implements KVDatabase<String, User> {

    HashMap<String, User> users;

    public MemoryUserDB(HashMap<String, User> defaultUsers) {
        users = defaultUsers;
    }

    public MemoryUserDB() {
        users = new HashMap<>();
    }

    @Override
    public User getValue(String name) {
        return users.get(name);
    }

    @Override
    public void setValue(String name, User user) {
        users.put(name, user);
    }
}
