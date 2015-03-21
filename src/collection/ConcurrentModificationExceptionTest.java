package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * 使用iterator的時候，不要對Collection使用remove或add，而是去使用iterator所提供的remove或add
 * 
 * @author coreyou
 *
 */
public class ConcurrentModificationExceptionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 上面這段會發生ConcurrentModificationException
		 */
//		List<String> myList = new ArrayList<String>();
//
//		myList.add("1");
//		myList.add("2");
//		myList.add("3");
//		myList.add("4");
//		myList.add("5");
//
//		Iterator<String> it = myList.iterator();
//		while (it.hasNext()) {
//			String value = it.next();
//			System.out.println("List Value:" + value);
//			if (value.equals("3"))
//				myList.remove(value);
//		}
//
//		Map<String, String> myMap = new HashMap<String, String>();
//		myMap.put("1", "1");
//		myMap.put("2", "2");
//		myMap.put("3", "3");
//
//		Iterator<String> it1 = myMap.keySet().iterator();
//		while (it1.hasNext()) {
//			String key = it1.next();
//			System.out.println("Map Value:" + myMap.get(key));
//			if (key.equals("2")) {
//				myMap.put("1", "4");
//				// myMap.put("4", "4");
//			}
//		}
		
		/*
		 * 下面這段使用不同的Collection型態，所以不會發生Exception
		 */
		List<String> myList = new CopyOnWriteArrayList<String>();

		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		myList.add("5");

		Iterator<String> it = myList.iterator();
		while (it.hasNext()) {
			String value = it.next();
			System.out.println("List Value:" + value);
			if (value.equals("3")) {
				myList.remove("4");
				myList.add("6");
				myList.add("7");
			}
		}
		System.out.println("List Size:" + myList.size());

		Map<String, String> myMap = new ConcurrentHashMap<String, String>();
		myMap.put("1", "1");
		myMap.put("2", "2");
		myMap.put("3", "3");

		Iterator<String> it1 = myMap.keySet().iterator();
		while (it1.hasNext()) {
			String key = it1.next();
			System.out.println("Map Value:" + myMap.get(key));
			if (key.equals("1")) {
				myMap.remove("3");
				myMap.put("4", "4");
				myMap.put("5", "5");
			}
		}

		System.out.println("Map Size:"+myMap.size());
	}

}
