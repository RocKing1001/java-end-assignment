package me.piguy.assignment;

import me.piguy.assignment.database.CollectionDatabase;
import me.piguy.assignment.encryption.Encryption;
import me.piguy.assignment.encryption.NoEncryption;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ConfigurationManager {
    public CollectionDatabase database = new CollectionDatabase();
    public Encryption encryption;
    public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public ConfigurationManager() {
       encryption = new NoEncryption();
    }

}
