package me.piguy.assignment.database;

/**
 * Key value database
 * @param <K> Key
 * @param <V> Value
 */
public interface KVDatabase<K,V> extends Database {
    public V getValue(K key);
    public void setValue(K key, V value);
}
