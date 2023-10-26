package me.piguy.assignment.database;

import me.piguy.assignment.models.Item;

import java.util.UUID;

/**
 * An in memory key-value database which makes use of a hashmap
 * Current purpose of this database is to store products
 */
public class MemoryProductDB extends KVPersistentDB<UUID, Item> {
    @Override
    protected String getOutputFileName() {
        return "ProductDB.dat";
    }
}
