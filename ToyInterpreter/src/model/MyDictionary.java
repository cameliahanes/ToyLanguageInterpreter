package model;
import java.util.Collection;
import java.util.HashMap;

public class MyDictionary<K, V> implements MyIDictionary<K, V>{
	private HashMap<K, V> mapp;
	
	public MyDictionary(HashMap<K, V> mapp){
		this.mapp = mapp;
	}
	
	@Override
	public V put(K key, V val){
		return this.mapp.put(key, val);
	}
	
	@Override
	public V get(K key){
		return this.mapp.get(key);
	}
	
	@Override
	public Collection<V> values(){
		return this.mapp.values();
	}
	
	@Override
	public V remove(K fd){
		return this.mapp.remove(fd);
	}
	
	@Override
	public String toString(){
		String res = "";
		
		boolean ok = false;
		for ( HashMap.Entry<K, V> entry : this.mapp.entrySet()){
			if ( ok )
				res += "\n";
			res += entry.getKey().toString() + " -> " + entry.getValue().toString();
			ok = true;
		}
		return res;
	}

	
}
