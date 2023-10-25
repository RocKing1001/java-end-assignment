package me.piguy.assignment.database;

import me.piguy.assignment.models.User;

import java.util.List;

/**
 * Key value database
 * @param <K> Key
 * @param <V> Value
 */
public interface KVDatabase<K,V> {
    public V getValue(K key);
    public void setValue(K key, V value);

    public List<V> getAllValues();
}
