package me.piguy.assignment.database;

import me.piguy.assignment.models.Item;

import java.util.HashMap;
import java.util.List;

/**
 * An in memory key-value database which makes use of a hashmap
 * Current purpose of this database is to store users
 */
public class MemoryProductDB implements KVDatabase<String, Item> {

    private HashMap<String, Item> items;

    public MemoryProductDB(HashMap<String, Item> defaultUsers) {
        items = defaultUsers;
    }

    public MemoryProductDB() {
        items = new HashMap<>();
    }

    @Override
    public Item getValue(String name) {
        return items.get(name);
    }

    @Override
    public void setValue(String name, Item item) {
        items.put(name, item);
    }

    @Override
    public void dropValue(String key) {
        items.remove(key);
    }

    @Override
    public List<Item> getAllValues() {
        return items.values().stream().toList();
    }
}
