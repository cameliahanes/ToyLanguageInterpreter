package model;

import java.util.Collection;
import java.util.HashMap;


public interface MyIDictionary<K, V> {
    V get( K key );
    V put( K key, V val );
    Collection<V> values();
    V remove(K fd);
    HashMap<K, V> toHashMap();
    MyIDictionary<K, V> clone();
}
