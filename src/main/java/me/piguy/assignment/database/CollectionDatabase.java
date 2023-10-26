package me.piguy.assignment.database;

import me.piguy.assignment.models.Item;
import me.piguy.assignment.models.Order;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/**
 * A collection database which holds references to multiple KV databases
 */
public class CollectionDatabase implements Database<DBCollections, KVPersistentDB<?,?>> , Serializable {
    private final HashMap<DBCollections, KVPersistentDB<?,?>> dbCollections;

    public CollectionDatabase() {
        this.dbCollections = new HashMap<>();

        dbCollections.put(DBCollections.Users, new MemoryUserDB());
        dbCollections.put(DBCollections.Products, new MemoryProductDB());
        dbCollections.put(DBCollections.Orders, new MemoryOrderDB());
    }

    public KVPersistentDB<UUID, Item> getProducts() {
        return (KVPersistentDB<UUID, Item>) getCollection(DBCollections.Products);
    }

    public KVPersistentDB<UUID, Order> getOrders() {
        return (KVPersistentDB<UUID, Order>) getCollection(DBCollections.Orders);
    }

    @Override
    public KVPersistentDB<?, ?> getCollection(DBCollections collection) {
        return dbCollections.get(collection);
    }

    @Override
    public void set(DBCollections collection, KVPersistentDB<?, ?> value) {
        dbCollections.put(collection, value);
    }
}
