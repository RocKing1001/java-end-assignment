package me.piguy.assignment;

import me.piguy.assignment.database.*;
import me.piguy.assignment.encryption.Encryption;
import me.piguy.assignment.encryption.NoEncryption;
import me.piguy.assignment.models.User;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ConfigurationManager {
    public TestCollectionDatabase database = new TestCollectionDatabase();
    public Encryption encryption;
    public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public ConfigurationManager() {
       encryption = new NoEncryption();
    }

}
