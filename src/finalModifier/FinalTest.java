package finalModifier;

public class FinalTest {
	
	public static void main(String[] args) {
		// 以下三個final變數都會被當作"巨集變數"處理，初值指定了以後，會在編譯時期就確定，變數本質會直接對應成一個值，程式中所有使用到該變數的地方都會被替換成值。
		final int a = 5 + 2;
		final double b = 1.2 / 3;
		// 在程式中，book被看作"測試JAVA: 99.0"。而JAVA會將使用過的字串丟進字串快取記憶體池，以後可以快速指向使用。
		final String book =	"測試JAVA: " + 99.0;	
		
		// 變數的值需要呼叫String類別的方法，所以沒有辦法在編譯時期就確定，所以不會被當作"巨集變數"處理。
		final String book2 = "測試JAVA: " + String.valueOf(99.0);	// 在程式中，book2被看作"測試JAVA: " + "99.0"
		
		System.out.println(book == "測試JAVA: 99.0");	// true
		System.out.println(book2 == "測試JAVA: 99.0");	// false
		
		FinalTest finalTest = new FinalTest();
		finalTest.display();
	}
	
	String s1 = "測試JAVA";
	String s2 = "測試" + "JAVA";	// 兩個字串值進行連接運算，會在編譯時期就確定，系統會指向字串池中的"測試JAVA"
	String str1 = "測試";
	String str2 = "JAVA";
	final String str3 = "測試";
	final String str4 = "JAVA";
	final String str5;
	final String str6;
	final String str7;
	final String str8;
	// 雖然加上了final，但是只有在定義變數時直接給值的final變數，才會執行巨集替換。		
	{
		str5 = "測試";
		str6 = "JAVA";
	}
		
	public FinalTest() {
		// TODO Auto-generated constructor stub
		str7 = "測試";
		str8 = "JAVA";
	}
	
	public void display() {
		System.out.println(s1 == s2);	// true
		String s3 = str1 + str2;	// str1和str2是普通變數，所以編譯器不會執行巨集替換，無法在編譯時期確定s3的值，也不會指向字串池中的"測試JAVA"。除非將str1和str2加上final
		String s4 = str3 + str4;	// 將str1和str2加上final來測試
		String s5 = str5 + str6;
		String s6 = str7 + str8;
		System.out.println(s1 == s3);	// false
		System.out.println(s1 == s4);	// true
		System.out.println(s1 == s5);	// false
		System.out.println(s1 == s6);	// false
	}
}
