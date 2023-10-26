package me.piguy.assignment.database;

import me.piguy.assignment.models.Role;
import me.piguy.assignment.models.User;

import java.util.HashMap;
import java.util.List;

/**
 * An in memory key-value database which makes use of a hashmap
 * Current purpose of this database is to store users
 */
public class MemoryUserDB extends KVPersistentDB<String, User> {

    @Override
    protected String getOutputFileName() {
        return "UserDB.dat";
    }

    @Override
    protected HashMap<String, User> getSampleData() {
        HashMap<String, User> hashMap = new HashMap<>();
        hashMap.put("admin", new User("admin", Role.IT, "root"));
        return hashMap;
    }
}
