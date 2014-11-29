package genericKnowledge;
/**
 * 泛型(generic)使用<>括住一個代表型態的名稱，之後就可以先使用該名稱來宣告變數，
 * 名稱所代表的型態是未定的，你要在建立物件時指定型態。
 * 限定泛型邊界的方法: 例如class Duck<T extends Animal> {}，T只能限定是Animal的子類別。
 * 
 * 本例和NodeTest.java連動。
 * 
 * @author coreyou
 *
 * @param <T>
 */
public class Node<T> {
	Node<T> next;
	private T value;
	
	T getValue() {
		return value;
	}
	
	void setvalue(T value) {
		this.value = value;
	}
}
