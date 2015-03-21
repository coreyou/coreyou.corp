package coreyou.corp;

import java.io.UnsupportedEncodingException;

//import org.apache.commons.codec.binary.Base64;
import java.util.*;
/**
 * Base64是一種能將任意Binary資料用64種字元組合成字串的方法，
 * 而這個Binary資料和字串資料彼此之間是可以互相轉換的，十分方便。
 * 在實際應用上，Base64除了能將Binary資料可視化之外，也常用來表示字串加密過後的內容。
 * 
 * @author coreyou
 *
 */
public class Base64Codec {
	public static void main(String[] args) {
		/*
		 * 使用apache.commons.codec.binary.Base64
		 */
//		Base64 base64 = new Base64();
//		String text = "字串文字";
//		byte[] textByte;
//		try {
//			textByte = text.getBytes("UTF-8");
//			//編碼
//			String encodedText = base64.encodeToString(textByte);
//			System.out.println(encodedText);
//			//解碼
//			System.out.println(new String(base64.decode(encodedText), "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/*
		 * JAVA 8
		 */
		Base64.Decoder decoder = Base64.getDecoder();
		Base64.Encoder encoder = Base64.getEncoder();
		String text = "字串文字";
		byte[] textByte;
		try {
			textByte = text.getBytes("UTF-8");
			//編碼
			String encodedText = encoder.encodeToString(textByte);
			System.out.println(encodedText);
			//解碼
			System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
