package genericKnowledge;

import java.util.Arrays;

class Basket<T> {
    T[] things;
    Basket(T... things) {
        this.things = things;
    }
    
    @Override
    public boolean equals(Object o) {
    	/*
    	 * 下面那行程式不能寫成 if(o instanceof Basket<T>)，
    	 * 在 程式中instanceof對Basket<T>的型態判斷是不合法的，
    	 * 因為Java的泛型所採用的是型態抹除，也就是說，程式中泛型語法的 型態指定，僅提供編譯器使用，執行時期無法獲型態資訊，
    	 * 因而instanceof在執行時期比對時，僅能針對Basket型態比對，無法針對當中的泛型實 際型態進行比對。
    	 */
        if(o instanceof Basket<?>) {	// 不能寫成 if(o instanceof Basket<T>) { ，否則會有編譯錯誤
            Basket that = (Basket) o;
            return Arrays.deepEquals(this.things, that.things) && this.getClass() == that.getClass();
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int sum = 1;
        for(T t : things) {
            sum = sum * 41 + t.hashCode();
        }
        return 41 * sum + things.hashCode();
    }
}

public class genericEqual {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Basket<Integer> b1 = new Basket<Integer>(1, 2);
        Basket<Integer> b2 = new Basket<Integer>(1, 2);
        Basket<Integer> b3 = new Basket<Integer>(2, 2);
        Basket<String> b4 = new Basket<String>("1", "2");
        System.out.println(b1.equals(b2));       // true
        System.out.println(b1.equals(b3));       // false
        System.out.println(b1.equals(b4));       // false
        
        /* 
         * Basket<Integer>與Basket<String>若是視作不同的型態，則b1與b2 應視為不相等，
         * 實際上，由於Java採用型態抹除的方式，結果就是認為在這種情況下，b1與b2是相等的。
         */
        Basket<String> b5 = new Basket<String>();
        Basket<Integer> b6 = new Basket<Integer>();
        System.out.println(b5.equals(b6));    // true        
	}

}
