package coreyou.corp;

import java.io.UnsupportedEncodingException;

//import org.apache.commons.codec.binary.Base64;
import java.util.*;

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
