package accessChildObj;
/**
 * 1.
 * 參考類別變數呼叫成員變數的時候，值會取決於參考類別；
 * 參考類別變數呼叫方法的時候，方法會取決於實際類別，除非是static方法。
 * 
 * 系統記憶體中，Child物件儲存了自己的成員變數(count)以外，還存了其父類別的成員變數(count)。
 * 
 * 2.
 * 子類別被初始化的時候，會為自己與自己所繼承的成員變數配置記憶體，但是所繼承的部分會隱藏起來，必須使用super關鍵字來呼叫。
 * 
 * @author coreyou
 *
 */
class Father {
	int count = 2;
	public void display() {
		System.out.println(this.count);
	}
	public Father getThis() {
		return this;
	}
	public void info() {
		System.out.println("Father class method");
	}
}

class Child extends Father {
	int count = 20;
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println(this.count);
	}
	@Override
	public void info() {
		// TODO Auto-generated method stub
		System.out.println("Child class method");
	}
	public void accessSuperInfo() {
		super.info();
	}
	public Father getSuper() {
		return super.getThis();
	}
}

public class MemberAndMethodTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("方法與成員變數的呼叫: ");
		Father f = new Father();
		System.out.println(f.count);	// 2
		f.display();	// 2
		
		Child c = new Child();	
		System.out.println(c.count);	// 20
		c.display();	// 20
		
		Father fc = new Child();
		System.out.println(fc.count);	// 2，成員變數會用參考類別的，也就是Father的
		fc.display();	// 20，方法會用實際類別的，也就是Child的
		
		Father fc2 = c;
		System.out.println(fc2.count);	// 2，成員變數會用參考類別的
		fc2.display();	// 20，方法會用實際類別的
		
		System.out.println("super怎麼用: ");
		Child child = new Child();
		Father father = child.getSuper();	// 因為呼叫的時候使用child，所以就算執行到father的getThis(retrun this;)，最後回傳的this也是child。
		System.out.println("child和father所參考的物件是否相同:" + (child == father) + ", (" + father.getClass() + " & " + child.getClass() + ")");
		System.out.println("存取child的成員變數: " + child.count);	// 成員變數會用參考類別的，也就是Child的
		System.out.println("存取((Father)child)的成員變數: " + ((Father)child).count);	// 強制向上轉型，參考類別變為Father
		System.out.println("存取father的成員變數: " + father.count);	// 成員變數會用參考類別的，也就是Father的
		child.info();	// 方法會用實際類別的，也就是Child的
		father.info();	// 方法會用實際類別的，也就是Child的
		child.accessSuperInfo();
	}

}
