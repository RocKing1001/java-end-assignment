package me.piguy.assignment.database;

import java.util.HashMap;

/**
 * A collection database which holds references to multiple KV databases
 */
public class CollectionDatabase implements Database<DBCollections, KVDatabase<?,?>> {
    HashMap<DBCollections, KVDatabase<?,?>> dbCollections;

    public CollectionDatabase() {
        this.dbCollections = new HashMap<>();

        dbCollections.put(DBCollections.Users, new MemoryUserDB());
        dbCollections.put(DBCollections.Products, new MemoryProductDB());
    }

    @Override
    public KVDatabase<?, ?> getCollection(DBCollections collection) {
        return dbCollections.get(collection);
    }

    @Override
    public void set(DBCollections collection, KVDatabase<?, ?> value) {
        throw new RuntimeException("Not implemented");
    }

}
