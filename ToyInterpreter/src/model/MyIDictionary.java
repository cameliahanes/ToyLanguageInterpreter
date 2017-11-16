package model;

import java.util.Collection;

public interface MyIDictionary<K, V> {
	V get( K key );
	V put( K key, V val );
	Collection<V> values();
	V remove(K fd);
}
