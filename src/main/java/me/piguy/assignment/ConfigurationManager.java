package me.piguy.assignment;

import me.piguy.assignment.database.KVDatabase;
import me.piguy.assignment.database.MemoryUserDB;
import me.piguy.assignment.encryption.Encryption;
import me.piguy.assignment.encryption.NoEncryption;
import me.piguy.assignment.models.User;

public class ConfigurationManager {
    public KVDatabase<String, User> userDB;
    public Encryption encryption;

    public ConfigurationManager() {
       encryption = new NoEncryption();
       userDB = new MemoryUserDB();
    }

}
