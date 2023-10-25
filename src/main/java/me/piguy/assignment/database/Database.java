package me.piguy.assignment.database;

/**
 * @param <I> Database collection identifier
 */
public interface Database<I, V> {
     public V getCollection(I collection);
     public void set(I collection, V value);
}