package me.piguy.assignment.database;

import me.piguy.assignment.models.Item;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/**
 * A collection database which holds references to multiple KV databases
 */
public class CollectionDatabase implements Database<DBCollections, KVDatabase<?,?>> , Serializable {
    HashMap<DBCollections, KVDatabase<?,?>> dbCollections;

    public CollectionDatabase() {
        this.dbCollections = new HashMap<>();

        dbCollections.put(DBCollections.Users, new MemoryUserDB());
        dbCollections.put(DBCollections.Products, new MemoryProductDB());
    }

    public KVDatabase<UUID, Item> getProducts() {
        return (KVDatabase<UUID, Item>) getCollection(DBCollections.Products);
    }

    @Override
    public KVDatabase<?, ?> getCollection(DBCollections collection) {
        return dbCollections.get(collection);
    }

    @Override
    public void set(DBCollections collection, KVDatabase<?, ?> value) {
        dbCollections.put(collection, value);
    }
}
