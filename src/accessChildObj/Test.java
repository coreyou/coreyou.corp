package accessChildObj;
/**
 * ==| 子類別存取父類別可能遇到的問題 |==
 * 
 * Java物件是由建構子建立的嗎? 事實上，建構子只是負責對Java物件執行成員變數的初始化，在執行建構子以前，該物件所佔的記憶體已經被分配下來了。
 * 所以在本例呼叫new Derived()的時候，第一步會先分配記憶體給Derived的i、Base的i，並給予int型態的預設值: 0。
 * 第二步會執行Base的建構子，經過編譯以後，除了System.out.println(this.i);和this.display();還會有成員變數(i = 2)會被解析到建構子內，
 * 這裡的this都代表Derived物件，但是在System.out.println(this.i);卻印出2，因為它的編譯時期型態是Base，但是在呼叫方法this.display();的時候，卻是使用它實際參考到的物件。
 * Derived的j此時還沒有經過建構子給值，所以會印出預設的0。
 * 第三步是執行Derived的建構子，印出33。
 * 
 * 這裡主要就是在敘述存取成員變數和方法的差別；以及在父類別建構子中，不要去呼叫子類別重新定義的方法，因為這個方法會在子類別建構子之前執行，某些子類別的成員變數值會錯誤。
 * 
 * @author coreyou
 *
 */
class Base {
	private int i = 2;
	public Base() {
		System.out.println(this.i);	// 印出2，因為它的編譯時期型態是Base。
		this.display();	// 會去呼叫Derived.display()，因為它實際參考一個Derived物件，可以用System.out.println(this.getClass());去驗證。但是Derived的i還沒有經過建構子給值，所以為預設的0。
	}
	public void display() {
		System.out.println(i);
	}
}

class Derived extends Base {
	private int i = 22;
	private int j = 33;
	public Derived() {
		i = 222;
		this.display();	// 印出33。
	}
	public void display() {
		System.out.println(j);
	}
}

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Derived();	// 依序印出2 0 33
	}

}
