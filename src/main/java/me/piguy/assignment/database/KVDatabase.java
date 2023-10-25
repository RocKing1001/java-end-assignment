package me.piguy.assignment.database;

import me.piguy.assignment.models.User;

import java.io.Serializable;
import java.util.List;

/**
 * Key value database
 * @param <K> Key
 * @param <V> Value
 */
public interface KVDatabase<K,V> extends Serializable {
    public V getValue(K key);
    public void setValue(K key, V value);

    public List<V> getAllValues();
}
