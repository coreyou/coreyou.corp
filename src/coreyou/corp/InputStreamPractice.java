package coreyou.corp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class InputStreamPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStreamPractice isp = new InputStreamPractice();
		//isp.testInputStream();
		//isp.testFileStream();
		//isp.testBufferedStream();
		//isp.testDataStream();
		isp.testObjectStream();
	}
	
	/**
	 * inputStream and outputStream
	 */
	public void testInputStream() {
		byte[] b = new byte[5];
		int numberOfInsertString = 0;
		try { 
            System.out.print("輸入字元: "); 
            System.out.println("輸入字元十進位表示: " + System.in.read()); // 一次讀取一個位元組
            System.out.println("換行字元十進位表示: " + System.in.read());
            numberOfInsertString = System.in.read(b);
            System.out.println("read(" + numberOfInsertString + "): " + System.in.read(b));
            System.out.println("inputStream.available: " + System.in.available());
            System.out.println("inputStream.markSupportedl: " + System.in.markSupported());
        } 
        catch(IOException e) { 
            e.printStackTrace(); 
        } 
	}
	
	/**
	 * fileInputStream and fileOutputStream
	 */
	public void testFileStream() {
		try { 
            byte[] buffer = new byte[1024]; 

            FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\coreyou\\workspace\\coreyou.corp\\src\\coreyouCorp.properties")); 
            FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\coreyou\\workspace\\coreyou.corp\\src\\coreyouCorp_copy.properties")); 

            System.out.println("複製檔案：" + fileInputStream.available() + "位元組"); 
                    
            int length = -1;
            // 從來源檔案讀取資料至緩衝區 
            while((length = fileInputStream.read(buffer)) != -1) { 
                // 將陣列資料寫入目的檔案 
                fileOutputStream.write(buffer, 0, length);
            } 

            // 關閉串流 
            fileInputStream.close(); 
            fileOutputStream.close(); 

            System.out.println("複製完成"); 
        } 
        catch(ArrayIndexOutOfBoundsException e) { 
            System.out.println("using: java FileStreamDemo src des"); 
            e.printStackTrace(); 
        } 
        catch(IOException e) { 
            e.printStackTrace(); 
        } 
	}
	
	public void testBufferedStream() {
        try { 
            byte[] data = new byte[1]; 
            File srcFile = new File("C:\\Users\\coreyou\\workspace\\coreyou.corp\\src\\coreyouCorp.properties"); 
            File desFile = new File("C:\\Users\\coreyou\\workspace\\coreyou.corp\\src\\coreyouCorp_copy.properties"); 
 
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcFile)); 
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(desFile));
 
            System.out.println("複製檔案：" + srcFile.length() + "位元組"); 
            while(bufferedInputStream.read(data) != -1) { 
                bufferedOutputStream.write(data); 
            }
            
            // 將緩衝區中的資料全部寫出 
            bufferedOutputStream.flush();
 
            // 關閉串流 
            bufferedInputStream.close(); 
            bufferedOutputStream.close(); 
        } 
        catch(ArrayIndexOutOfBoundsException e) { 
            System.out.println("using: java UseFileStream src des"); 
            e.printStackTrace(); 
        } 
        catch(IOException e) { 
            e.printStackTrace(); 
        } 
	}
	
	public void testDataStream() {
		String name = "Kevin";
		int score = 100;
		
		try { 
			DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("C:\\Users\\coreyou\\workspace\\coreyou.corp\\src\\coreyouCorp_copy.properties")); 
            
            dataOutputStream.writeUTF(name); 
            dataOutputStream.writeInt(score); 
 
            dataOutputStream.flush(); 
            dataOutputStream.close(); 
            
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream("C:\\Users\\coreyou\\workspace\\coreyou.corp\\src\\coreyouCorp_copy.properties")); 
             
            String nameInput = dataInputStream.readUTF(); 
            int scoreIntput = dataInputStream.readInt(); 
            System.out.println("name= " + nameInput + ", score= " + scoreIntput);
            dataInputStream.close(); 
        } 
        catch(IOException e) { 
            e.printStackTrace(); 
        } 
	}
	
	public void testObjectStream() {
		String[] students = {new String("string first"), new String("string second")}; 
		
		try { 
            ObjectOutputStream objOutputStream = new ObjectOutputStream(new FileOutputStream("C:\\Users\\coreyou\\workspace\\coreyou.corp\\src\\coreyouCorp_copy.properties")); 
            objOutputStream.writeObject(students); 
            objOutputStream.close(); 
            
            List list = new ArrayList();           
            try {
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\coreyou\\workspace\\coreyou.corp\\src\\coreyouCorp_copy.properties");
                ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream); 
                 
                while(fileInputStream.available() > 0) {
                    list.add(objInputStream.readObject());
                }
                objInputStream.close(); 
            } 
            catch(ClassNotFoundException e) { 
                e.printStackTrace(); 
            } 
            catch(IOException e) { 
                e.printStackTrace(); 
            }
     
            Object[] objs = list.toArray();
            System.out.println("name= " + objs[0] + ", score= ");
        } 
        catch(IOException e) { 
            e.printStackTrace(); 
        }
	}
	
}
