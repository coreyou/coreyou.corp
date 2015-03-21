package socketUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * 掃描指定主機上所開啟的連接埠（0~1023），這邊指定本機為對象建立Socket連線，
 * 如果某個連接埠有開啟，就會建立連線，此時顯示該連接埠開啟的訊息。
 * 
 * @author coreyou
 *
 */
public class ScanPort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            String hostname = "localhost";
            InetAddress address = InetAddress.getByName(hostname); 

            for(int i = 0; i < 1024; i++) { 
                try { 
                    Socket skt = new Socket(address, i); 
                    // 連線表示有開啟 Port 
                    System.out.println("\nPort: " + i + " Opened!"); 
                    skt.close(); 
                } 
                catch(IOException e) { 
                	// 每掃到一個未開啟的port就印出一個點
                    System.out.print("."); 
                    // 無法建立連線，沒有開啟 Port 
                } 
            } 
        } 
        catch(UnknownHostException e) { 
            e.printStackTrace();
        } 
	}

}
