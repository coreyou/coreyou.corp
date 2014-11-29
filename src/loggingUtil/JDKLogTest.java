package loggingUtil;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 1.使用JDK內建的Logging功能。
 * 
 * @author coreyou
 *
 */
public class JDKLogTest {
	public static Logger log = Logger.getLogger(JDKLogTest.class.toString());
	
	// 初始化記錄檔
	static {
		Handler console = new ConsoleHandler();	// 增加一個控制台輸出
		/*
		 * 設定級別(低到高): ALL、FINEST、FINER、FINE、CONFIG、INFO、WARNING、SEVERE、OFF
		 * 比設定級別低的訊息將不會被記錄
		 */
		console.setLevel(Level.FINE);
		
		log.addHandler(console);	// 增加到log中
	}
	
	public static void main(String[] args) {
		log.setLevel(Level.FINE);
		
		log.finest("finest");
		log.finer("finer");
		log.fine("fine");
		log.config("config");
		// JDK Log本來就預設對info以上的級別會有控制台輸出，所以下面三行的訊息都多輸出了一次
		log.info("info");
		log.warning("warning");
		log.severe("severe");
	}
}
