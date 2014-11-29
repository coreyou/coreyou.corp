package classInitTiming;
/**
 * ==| 類別成員變數(static member)的初始化時機 |==
 * 類別成員變數初始化 = 靜態初始化區塊
 * 
 * 系統會先幫INSTANCE、initPrice兩個類別成員變數分配記憶體空間，此時預設值為null和0.0，
 * 接下來，INSTANCE給值時建立Price實體，呼叫建構子，此時initPrice還是0.0，所以0.0 - 2.8導致currentPrice為-2.8。
 * 再來才是initPrice設定為10，又被靜態初始化區塊覆蓋為20，但是已經無法再影響INSTANCE了。
 * 所以如果把initPrice的初始化移到INSTANCE初始化的前面，就不會印出-2.8了。
 * 
 * @author coreyou
 *
 */
class Price {
//	static double initPrice = 20;	// 如果使用的是這段，測試結果會是兩個相同的數字。
	// 類別成員變數初始化 1
	final static Price INSTANCE = new Price(2.8);	
	// 類別成員變數初始化 2
	static double initPrice = 10;	// 如果加上final修飾字，那麼就不會有-2.8的情況出現，因為final的運作是直接把變數替代成對應值，並且在編譯的時期就被確定。
	double currentPrice;
	
	// 靜態初始化區塊
	static {
		System.out.println("執行靜態初始化區塊");
		initPrice = 20;
	}
	public Price(double discount) {
		// TODO Auto-generated constructor stub
		System.out.println("執行建構元: " + initPrice + " - " + discount);
		currentPrice = initPrice - discount;
	}
}

public class ClassInitTest {
	public static void main(String[] args) {
		System.out.println(Price.INSTANCE.currentPrice);	// 印出-2.8，由0 - 2.8來的
		
		Price p = new Price(2.8);
		System.out.println(p.currentPrice);	// 印出17.2，由20 - 2.8來的
	}
}
