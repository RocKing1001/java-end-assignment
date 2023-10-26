package me.piguy.assignment.database;

import me.piguy.assignment.models.Role;
import me.piguy.assignment.models.User;

import java.util.HashMap;

/**
 * An in memory key-value database which makes use of a hashmap
 * Current purpose of this database is to store users
 */
public class MemoryUserDB extends KVPersistentDB<String, User> {

    @Override
    protected String getOutputFileName() {
        return "UserDB.dat";
    }

    // I am not a huge fan of adding the users in here
    // But I am 98% sure that we will need to add an interface to manage the users the exam
    // So I will leave this at this for now, I already have a game plan on how to add a
    // management interface (kira theme plays)
    @Override
    @Deprecated
    protected HashMap<String, User> getSampleData() {
        HashMap<String, User> hashMap = new HashMap<>();
        hashMap.put("admin", new User("admin", Role.IT, "root"));
        hashMap.put("joey_nonsensejp", new User("Joey Bizinger", Role.Sales, "nonsense"));
        hashMap.put("cdawgva", new User("Connor Colquhoun", Role.Sales, "jumpking"));
        hashMap.put("notgrant", new User("Garnt \"Grant\" Maneetapho", Role.IT, "isekai"));
        return hashMap;
    }
}
