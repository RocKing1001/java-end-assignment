package me.piguy.assignment.database;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * CRUD ready persistent KV data store <br/>
 * CREATE: {@link #setValue(K, V)} <br/>
 * READ: {@link #getValue(K)}, {@link #getAllValues()} <br/>
 * UPDATE: {@link #setValue(K, V)} <br/>
 * DELETE: {@link #dropValue(K)} <br/>
 *
 * @param <K> Key
 * @param <V> Value
 */
public abstract class KVPersistentDB<K, V> implements Serializable {
    protected HashMap<K, V> data = new HashMap<>();

    public KVPersistentDB() {
        getPreppedFile();
        this.data = getDataFromFile();
    }


    // READ
    public final V getValue(K key) {
        return data.get(key);
    }

    public final List<V> getAllValues() {
        return data.values().stream().toList();
    }

    // CREATE, UPDATE
    public final void setValue(K key, V value) {
        storeValue(key, value);
        storeSerialisedData();
    }

    protected final void storeValue(K key, V value) {
        data.put(key, value);
    }

    // DELETE
    public final void dropValue(K key) {
        data.remove(key);
        storeSerialisedData();
    }


    // PERSISTENCE
    protected abstract String getOutputFileName();
    private File getPreppedFile() {
        File file = new File(getOutputFileName());
        try {
            if (file.createNewFile()) {
                System.out.printf("%n%nEmpty file %s created%n", getOutputFileName());
                System.out.println("Now writing retrieved initial data to the file");

                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(getSampleData());

                System.out.printf("Data successfully serialised to file %s%n%n%n", getOutputFileName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
    protected HashMap<K, V> getSampleData() {
        return new HashMap<>();
    }
    private HashMap<K,V> getDataFromFile() {
        HashMap<K,V> hashMap = new HashMap<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getOutputFileName()));
            hashMap = (HashMap<K, V>) ois.readObject();
        } catch (IOException | ClassNotFoundException e ) {
            System.err.println("BITCH" + e.getMessage());
        }

        return hashMap;
    }
    private void storeSerialisedData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getOutputFileName()));
            oos.writeObject(data);
            System.out.println("Stored changes to " + getOutputFileName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
