package socketUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 要取Socket物件的接受或輸出資訊，可以使用getInputStream()與getOutputStream()兩個方法。
 * 
 * 與StdInToSocket有關，請開啟ConnectDemo.java測試。
 *
 * @author coreyou
 *
 */
public class SocketToStdout implements Runnable {

	private Socket skt;

    public SocketToStdout(Socket skt) {
        this.skt = skt;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedReader sktReader;
        try {
            sktReader = new BufferedReader(new InputStreamReader(skt.getInputStream()));

            String sktMessage = null;
            while ((sktMessage = sktReader.readLine()) != null) {
                System.out.println(sktMessage);
            }

            skt.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
	}

}
