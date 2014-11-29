package setExtendToMap;

import java.util.Map;

/**
 * package的主要目的是把Set擴充成Map。
 * 這裡實作Map interface裡面的Entry interface
 * Map是由很多組Entry組成的，每一組Entry則是由一個key和一個value組成，
 * Map中的entrySet() method會回傳一個Set，這個Set包含所有Map中的key與value的組合，
 * 我們可以使用interator()跑過所有Set的內容，將Set中所有Entry的key和value使用getKey()和getValue()取出，
 * 實際code可以參考MapEntryDemo.java。
 * 
 * @author coreyou
 *
 * @param <K>
 * @param <V>
 */
public class SimpleEntry<K, V> implements Map.Entry<K, V>, java.io.Serializable {

	private final K key;
	private V value;
	
	public SimpleEntry(K key, V value) {
		// TODO Auto-generated constructor stub
		this.key = key;
		this.value = value;
	}
	
	public SimpleEntry(Map.Entry<? extends K, ? extends V> entry) {
		this.key = entry.getKey();
		this.value = entry.getValue();
	}
	
	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public V setValue(V value) {
		// TODO Auto-generated method stub
		V oldValue = this.value;
		this.value = value;
		return oldValue;
	}

	@Override
	public boolean equals(Object paramObject) {
		// TODO Auto-generated method stub
		if (paramObject == this) {
			return true;
		} 
		if (paramObject.getClass() == SimpleEntry.class) {
			SimpleEntry se = (SimpleEntry)paramObject;
			return se.getKey().equals(getKey());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return key == null ? 0 : key.hashCode();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return key + " = " + value;
	}
}
