import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class HashMap<K, V> implements HashMapInterface<K, V>, Gradable<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    public HashMap() {
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
    }

    /**
     * Finds the index of a given key in the table that is
     * not removed.
     * Should run in O(1) with a good hash function, O(n) otherwise.
     * @param key the key to find
     * @return the index of the key; -1 if key not found
     */
    private int findKey(K key) {
        int hash = Math.abs(key.hashCode()) % table.length;
        for (int i = hash; i < table.length; i++) {
            if (table[i] == null) {
                return -1;
            } else if (table[i].getKey().equals(key) && !table[i].isRemoved()) {
                return i;
            }
        }
        for (int i = 0; i < hash; i++) {
            if (table[i] == null) {
                return -1;
            } else if (table[i].getKey().equals(key) && !table[i].isRemoved()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public V add(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        if (((double) (size + 1) / (double) (table.length)) > MAX_LOAD_FACTOR) {
            MapEntry<K, V>[] old = table;
            table  = (MapEntry<K, V>[]) new MapEntry[2 * table.length];
            size = 0;
            for (int i = 0; i < old.length; i++) {
                if (old[i] != null && !old[i].isRemoved()) {
                    add(old[i].getKey(), old[i].getValue());
                }
            }
        }
        int hash = Math.abs(key.hashCode()) % table.length;
        int foundKey = findKey(key);
        MapEntry<K, V> newEntry = new MapEntry<K, V>(key, value);
        if (foundKey != -1) {
            V oldValue = table[hash].getValue();
            table[hash] = newEntry;
            return oldValue;
        } else {
            for (int i = hash; i < table.length; i++) {
                if (table[i] == null || table[i].isRemoved()) {
                    table[i] = newEntry;
                    size++;
                    return null;
                }
            }
            for (int i = 0; i < hash; i++) {
                if (table[i] == null || table[i].isRemoved()) {
                    table[i] = newEntry;
                    size++;
                    return null;
                }
            }
        }
        return null;
    }


    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int foundKey = findKey(key);
        if (size == 0 || foundKey == -1 || table[foundKey].isRemoved()) {
            return null;
        } else {
            V removed = table[foundKey].getValue();
            table[foundKey].setRemoved(true);
            size--;
            return removed;
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int foundKey = findKey(key);
        if (size == 0 || foundKey == -1 || table[foundKey].isRemoved()) {
            return null;
        }
        return table[foundKey].getValue();
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (size == 0) {
            return false;
        }
        int foundKey = findKey(key);
        return (foundKey != -1);
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public MapEntry<K, V>[] toArray() {
        return table;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> keys = new HashSet<K>(table.length);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                keys.add(table[i].getKey());
            }
        }
        return keys;
    }

    @Override
    public List<V> values() {
        ArrayList<V> values = new ArrayList(table.length);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                values.add(table[i].getValue());
            }
        }
        return values;
    }

}
