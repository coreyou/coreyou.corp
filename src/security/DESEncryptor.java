package security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DESEncryptor {
	private Cipher cipher;
	private Key key;
	
	public DESEncryptor(String key) throws Exception {
		// TODO Auto-generated constructor stub
		if ("".equals(key)) {
			key = "kdjc";
		}
		setKey(key.getBytes());
		cipher = Cipher.getInstance("DES");
	}
	
	// 從指定的字串製成密鑰，密鑰所需的字元陣列長度為8位，不足及超過都要處理
	private void setKey(byte[] keyByte) throws Exception 
    {         
        byte[] tempByte = new byte[8];
        // 將原始字元陣列轉換為8位
        for (int i = 0; i < keyByte.length && i < tempByte.length; i++)
        {
            tempByte[i] = keyByte[i];
        }
        // 設定密鑰
        key = new SecretKeySpec(tempByte, "DES");    
    }
	
	// 將byte陣列轉換16進制值的字串，如：byte[]{1,18}轉換為：0112     
    public String byte2Hex(byte[] arg_bteArray) throws Exception 
    {
        int intStringLength = arg_bteArray.length;
        int intTemp = 0; 
        StringBuffer sb = new StringBuffer(intStringLength * 2);
        for (int i = 0; i < intStringLength; i++)
        {
            intTemp = (int)arg_bteArray[i];
            //負數需要轉成正數
            if(intTemp < 0) 
            {
                intTemp = intTemp + 256;
            }
            // 小於0F需要補0
            if (intTemp < 16)
            {
                sb.append("0");
            }
            sb.append(Integer.toString(intTemp, 16));
        }
        return sb.toString();
     }

    
    //將16進制值的字串轉成byte陣列        
    public byte[] hex2Byte(String arg_strHexString) throws Exception 
    {
        byte[] arrByteDAta = arg_strHexString.getBytes();
        int intStringLength = arrByteDAta.length;
        byte[] aryRetuenData = new byte[intStringLength / 2];
        for (int i = 0; i < intStringLength; i = i + 2)
        {
            aryRetuenData[i / 2] = (byte)Integer.parseInt(new String(arrByteDAta, i, 2), 16);
        }
        return aryRetuenData;
    }

	// 加密字串
	public byte[] doEncrypt(byte[] arg_bteArray) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(arg_bteArray);
	}
     
	public String encrypt(String arg_strToEncriptString) throws Exception {
		return byte2Hex(doEncrypt(arg_strToEncriptString.getBytes()));
	}
     
	public byte[] doDecrypt(byte[] arg_bteArray) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(arg_bteArray);
	}

	// 解密字串
	public String decrypt(String arg_strToDecriptString) throws Exception {
		return new String(doDecrypt(hex2Byte(arg_strToDecriptString)));
	}
}
