package coreyou.corp;

import java.security.*;
import java.util.Arrays;

public class MD5 {

	// 密碼必須先經過MD5加密才能存到資料庫中
	static String md5(String str) {
		String md5=null;
	    try {
	    	MessageDigest md = MessageDigest.getInstance("MD5");
	    	byte[] b = str.getBytes();	// 字串轉2進制
	    	System.out.println(Arrays.toString(b));
	    	byte[] barr = md.digest(b);  //將 byte 陣列加密
	    	//將 byte 陣列轉成 16 進制
	    	StringBuffer sb = new StringBuffer();  
	    	for (int i = 0; i < barr.length; i++) {
	    		sb.append(byte2Hex(barr[i]));
	    	}
	    	
	    	String hex = sb.toString();
	    	md5 = hex.toUpperCase(); //一律轉成大寫
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return md5;
	}
	static String byte2Hex(byte b) {
	    String[] h = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
	    int i = b;
	    if (i < 0) {
	    	i += 256;
	    }
	    return h[i/16] + h[i%16];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String plainText = "coreyou so handsome!";
		String encypText = md5(plainText);
		System.out.println(encypText);
	}

}
