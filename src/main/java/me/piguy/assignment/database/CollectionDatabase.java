package me.piguy.assignment.database;

import me.piguy.assignment.models.Item;
import me.piguy.assignment.models.Order;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/**
 * A collection database which holds references to multiple KV databases
 */
public class CollectionDatabase implements Database<DBCollections, KVDatabase<?,?>> , Serializable {
    private final HashMap<DBCollections, KVDatabase<?,?>> dbCollections;

    public CollectionDatabase() {
        this.dbCollections = new HashMap<>();

        dbCollections.put(DBCollections.Users, new MemoryUserDB());
        dbCollections.put(DBCollections.Products, new MemoryProductDB());
        dbCollections.put(DBCollections.Orders, new MemoryOrderDB());
    }

    public KVDatabase<UUID, Item> getProducts() {
        return (KVDatabase<UUID, Item>) getCollection(DBCollections.Products);
    }

    public KVDatabase<UUID, Order> getOrders() {
        return (KVDatabase<UUID, Order>) getCollection(DBCollections.Orders);
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
