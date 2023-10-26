package me.piguy.assignment.database;

import me.piguy.assignment.models.Order;

import java.util.UUID;

public class MemoryOrderDB extends KVPersistentDB<UUID, Order> {
    @Override
    protected String getOutputFileName() {
        return "OrderDB.dat";
    }
}
