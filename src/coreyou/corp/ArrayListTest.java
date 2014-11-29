package coreyou.corp;
/**
 * Author: coreyou
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ArrayListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        
        List<String> list = new ArrayList<String>();
        
        System.out.println("輸入名稱(quit結束)"); 
        while(true) { 
            System.out.print("# "); 
            String input = scanner.next(); 
 
            if(input.equals("quit"))
                 break; 
            list.add(input); 
        }
        
        // 1.使用iterator來遍訪List中所有元素
        System.out.print("使用Iterators顯示輸入: "); 
        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println(); 
        
        // 2.使用for迴圈來遍訪List中所有元素
        System.out.print("使用for迴圈顯示輸入: "); 
        for(int i = 0; i < list.size(); i++) 
            System.out.print(list.get(i) + " "); 
        System.out.println(); 
        
        // 3.在J2SE 5.0以後，使用增強的for迴圈可以直接遍訪List的所有元素
        System.out.print("使用增強的for迴圈顯示輸入: ");
        for(String s : list) {
            System.out.print(s + " ");
        }       
        System.out.println(); 
	}

}
