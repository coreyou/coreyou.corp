package genericKnowledge;

import java.lang.reflect.Array;
/**
 * 各種泛型的應用:
 * 1. 將多維陣列轉換成一維陣列
 * 2. 用來宣告不定長度引數
 * 
 * @author coreyou
 *
 */
class ArrayUtil {
    @SuppressWarnings(value={"unchecked"})
    /*
     * 將泛型多維陣列轉換成泛型一維陣列
     */
    public static <T> T[] toOneByColumn(T[][] array) {
        T[] arr = (T[]) Array.newInstance(array[0].getClass().getComponentType(), array.length * array[0].length);
        for(int row = 0; row < array.length; row++) { 
            for(int column = 0; column < array[0].length; column++) { 
            	if (row == 0) {
            		arr[row * array.length + column] = array[row][column];
            	} else {
            		arr[(row + 1) * array.length + (column + 1) - array.length] = array[row][column];
            	}                
            } 
        }
        return arr;
    }
    
    /*
     * 泛型也可以用來宣告不定長度引數
     */
    public static <T> void show(T... values) {
    	for(T v : values) {
    		System.out.println(v);
    	}
    }
}

public class MultiDiArrayToOneDi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[][] arr1 = {{1, 2, 3}, {4, 5, 6}};
		
		for(Integer i : ArrayUtil.<Integer>toOneByColumn(arr1)) {
			System.out.println(i);
		}

		String[][] arr2 = {{"one",  "two",  "three"}, {"four", "five", "six"}};
		for(String s : ArrayUtil.<String>toOneByColumn(arr2)) {
			System.out.println(s);
		}
		
		ArrayUtil.<Integer>show(1, 2);
		ArrayUtil.<String>show("one", "two", "three");
	}

}
