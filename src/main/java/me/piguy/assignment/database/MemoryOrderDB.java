package me.piguy.assignment.database;

import me.piguy.assignment.models.Order;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MemoryOrderDB implements KVDatabase<UUID, Order> {

    private final HashMap<UUID, Order> orders = new HashMap<>();

    @Override
    public Order getValue(UUID key) {
        return orders.get(key);
    }

    @Override
    public void setValue(UUID key, Order value) {
        orders.put(key, value);
    }

    @Override
    public void dropValue(UUID key) {
        orders.remove(key);
    }

    @Override
    public List<Order> getAllValues() {
        return orders.values().stream().toList();
    }
}
