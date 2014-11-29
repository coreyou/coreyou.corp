package objectInitTiming;
/**
 * ==| 物件成員變數的初始化時機 |==
 * 非靜態初始化區塊 = 定義個別物件成員變數的時候 > 建構元中對執行的個別物件成員變數指定初值
 * 
 * 非靜態初始化區塊、定義個別物件成員變數最後都會被分析到建構元內。
 * 
 * @author coreyou
 *
 */
class Cat {
	String name;
	int age;
	
	public Cat(String name, int age) {
		// TODO Auto-generated constructor stub
		System.out.println("執行建構元");
		this.name = name;
		this.age = age;
	}
	
	// 定義個別物件成員變數
//	double weight = 2.3;	// 使用這段的話，會被row 25蓋掉
	
	// 非靜態初始化區塊
	{
		System.out.println("執行非靜態初始化區塊");
		weight = 2.0;
	}
	
	// 定義個別物件成員變數
	double weight = 2.3;	// 使用這段的話，會蓋掉row 25
	
	public String toString() {
		return "Cat [name=" + name + ", age=" + age + ", weight=" + weight + "]";
	}
}

public class ObjectInitTest {
	public static void main(String[] args) {
		Cat cat = new Cat("Kitty", 2);
		System.out.println(cat);
		Cat c2 = new Cat("Jerfield", 3);
		System.out.println(c2);
	}
}
