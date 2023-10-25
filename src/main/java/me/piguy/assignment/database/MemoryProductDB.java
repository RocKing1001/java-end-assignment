package me.piguy.assignment.database;

import me.piguy.assignment.models.Item;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * An in memory key-value database which makes use of a hashmap
 * Current purpose of this database is to store users
 */
public class MemoryProductDB implements KVDatabase<UUID, Item> {

    private final HashMap<UUID, Item> items;

    public MemoryProductDB(HashMap<UUID, Item> defaultUsers) {
        items = defaultUsers;
    }

    public MemoryProductDB() {
        items = new HashMap<>();
    }

    @Override
    public Item getValue(UUID name) {
        return items.get(name);
    }

    @Override
    public void setValue(UUID id, Item item) {
        items.put(id, item);
    }

    @Override
    public void dropValue(UUID key) {
        items.remove(key);
    }

    @Override
    public List<Item> getAllValues() {
        return items.values().stream().toList();
    }
}
