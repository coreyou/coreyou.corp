package genericKnowledge;
/**
 * 本例為泛型(generic)之測試，
 * 本例與Node.java連動。
 * 
 * @author coreyou
 *
 */
public class NodeTest {

	public static void main(String[] args) {
		// 泛型使用String型態
		Node<String> first = new Node<String>();
		first.setvalue("first word");
		first.next = new Node<String>();
		first.next.setvalue("second word");
		System.out.println(first.getValue() + ", " + first.next.getValue());
		
		// 泛型使用int型態
		Node<Integer> third = new Node<Integer>();
		third.setvalue(100);
		third.next = new Node<Integer>();
		third.next.setvalue(200);
		System.out.println(third.getValue() + ", " + third.next.getValue());
	}
}
