package me.piguy.assignment;

import me.piguy.assignment.database.CollectionDatabase;
import me.piguy.assignment.encryption.Encryption;
import me.piguy.assignment.encryption.NoEncryption;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ConfigurationManager {
    private CollectionDatabase database = new CollectionDatabase();
    private Encryption encryption;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public ConfigurationManager() {
       setEncryption(new NoEncryption());
    }

    public CollectionDatabase getDatabase() {
        return database;
    }

    public void setDatabase(CollectionDatabase database) {
        this.database = database;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public ScheduledExecutorService getScheduler() {
        return scheduler;
    }
}
