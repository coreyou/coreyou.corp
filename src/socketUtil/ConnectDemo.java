package socketUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * 模擬Telnet程式，您可以用它來與遠端主機進行「以行為主」的文字或指令溝通，
 * 也就是每下一行文字或指令就按Enter鍵，然後程式會將您的指令傳送出去，
 * 並顯示遠端主機的回應訊息，為了同時處理遠端主機的回應與本機使用者的輸入，程式使用多執行緒。
 * 
 * 與SocketToStdout.java和StdInToSocket.java有關。
 * 
 * @author coreyou
 *
 */
public class ConnectDemo {

	public static void main(String[] args) {
		String hostname = "localhost";
	    int port = 25;
	    InetAddress address;
	    BufferedReader buf;
	    String read;

	    if(args.length > 1) {
	        hostname = args[0];
	        port = Integer.parseInt(args[1]);
	    }

	    try {
	        address = InetAddress.getByName(hostname);
	        try {
	            Socket skt = new Socket(address, port);
	            Thread sktToStd = new Thread(new SocketToStdout(skt));
	            Thread stdToSkt = new Thread(new StdInToSocket(skt));

	            sktToStd.start();
	            stdToSkt.start();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } catch(UnknownHostException e) {
	        System.out.println(e.toString());
	    }
	}
}
