package setExtendToMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class SetToMap<K, V> extends HashSet<SimpleEntry<K, V>>{
	
	/*
	 * (non-Javadoc)
	 * 實作清空所有key-value對的方法
	 * @see java.util.HashSet#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
	}
	
	/*
	 * 判斷是否包含某個key
	 */
	public boolean containsKey(Object key) {
		return super.contains(new SimpleEntry<K, V>((K) key, null));
	}
	
	/*
	 * 判斷是否包含某個value
	 */
	boolean containsValue(Object value) {
		for (SimpleEntry<K, V> se : this) {
			if (se.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 根據指定key取出對應的value
	 */
	public V get(Object key) {
		for (SimpleEntry<K, V> se : this) {
			if (se.getKey().equals(key)) {
				return se.getValue();
			}
		}
		return null;
	}
	
	/*
	 * 將指定key-value對放入集合中
	 */
	public V put(K key, V value) {
		add(new SimpleEntry<K, V>(key, value));
		return value;
	}
	
	/*
	 * 將另一個Map的key-value對放入該Map中
	 */
	public void putAll(Map<? extends K, ? extends V> m) {
		for (K key : m.keySet()) {
			add(new SimpleEntry<K, V>(key, m.get(key)));
		}
	}
	
	/*
	 * 根據指定Key刪除指定key-value對
	 */
	public V removeEntry(Object key) {
		for (Iterator<SimpleEntry<K, V>> it = this.iterator(); it.hasNext(); ) {
			SimpleEntry<K, V> en = (SimpleEntry<K, V>)it.next();
			if (en.getKey().equals(key)) {
				V v = en.getValue();
				it.remove();
				return v;
			}
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 獲得該Map中包含多少個key-value對
	 * @see java.util.HashSet#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return super.size();
	}
}
