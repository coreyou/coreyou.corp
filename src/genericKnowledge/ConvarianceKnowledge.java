package genericKnowledge;
/**
 * Java的泛型不支援共變性(convariance)與逆變性（Contravariance），
 * 如 果B是A的子型態，而Node<B>被視為一種Node<A>型態，則稱Node具有共變性（Covariance）或有彈性的（flexible）。
 * 如 果Node<A>被視為一種Node<B>型態，則稱Node具有逆變性（Contravariance）。
 * 如果不具共變性或逆變性，則Node是不可變 的（nonvariant）或嚴謹的（rigid）。
 * 
 * @author coreyou
 *
 * @param <T>
 */
class Element<T> {
    T value;
    Element<T> next;
    
    Element(T value, Element<T> next) {
        this.value = value;
        this.next = next;
    }
}

class Fruit {}

class Apple extends Fruit {
    @Override
    public String toString() {
        return "Apple";
    }
}

class Banana extends Fruit {
    @Override
    public String toString() {
        return "Banana";
    }
}

public class ConvarianceKnowledge {
	
	public static void main(String[] args) {
		Element<Apple> apple = new Element<Apple>(new Apple(), null);
//	    Element<Fruit> fruit = apple;  // 編譯錯誤，incompatible types，用下一列來達成
		Element<? extends Fruit> fruit = apple; // 類似共變性效果
		
		Element<Fruit> f = new Element<Fruit>(new Fruit(), null);
		Element<? super Apple> a = f;	// Java的泛型亦不支援逆變性，不過可以使用型態通配字元?與super來宣告變數，使其達到類似逆變性
		Element<? super Banana> b = f;
	}
	
}
