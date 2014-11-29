package setExtendToMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/**
 * Map中的entrySet() method會回傳一個Set，這個Set包含Map中所有key與value的組合，也就是Map中所有Entry，
 * 我們可以使用interator()跑過所有Set的內容，將Set中所有Entry的key和value使用getKey()和getValue()取出。
 * 
 * 一般而言，要取出Map中的值，是使用get()，指定參數是key值，回傳value，無法像是Entry一次能夠得到key以及value。
 * 
 * @author coreyou
 *
 */
public class MapEntryDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create a hash map
		HashMap hm = new HashMap();
		// Put elements to the map
		hm.put("Zara", new Double(3434.34));
		hm.put("Mahnaz", new Double(123.22));
		hm.put("Ayan", new Double(1378.00));
		hm.put("Daisy", new Double(99.22));
		hm.put("Qadir", new Double(-19.08));
			
		// Get a set of the entries
		Set set = hm.entrySet();
		// Get an iterator
		Iterator i = set.iterator();
		// Display elements
		while(i.hasNext()) {
		   Map.Entry me = (Map.Entry)i.next();
		   System.out.print(me.getKey() + ": ");
		   System.out.println(me.getValue());
		}
		System.out.println();
		// Deposit 1000 into Zara's account
		double balance = ((Double)hm.get("Zara")).doubleValue();
		hm.put("Zara", new Double(balance + 1000));
		System.out.println("Zara's new balance: " +
		hm.get("Zara"));
	}

}
