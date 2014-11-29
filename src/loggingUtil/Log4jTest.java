package loggingUtil;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
/**
 * 2.使用Apache Log4j來記錄log，
 * 要記得先在/src下建立log4j.properties的設定檔。
 * Build Path > Configure Build Path > Libraries > Add External JARs.. > 找到log4j-1.2.17.jar。
 * 
 * @author coreyou
 *
 */
public class Log4jTest {
	/*
	 * Log4j記錄檔紀錄器，
	 * Logger的名字就是getLogger的參數，相同名字的Logger只會有一個實例，如果建構一個同名的，Log4j會回傳先前的那個實例。
	 * Log4j有一個根紀錄器為rootLogger，他是所有Logger的父親。
	 */
	public static Logger log = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		String ss = "串接字串";
		/*
		 * 設定級別(低到高): ALL、TRACE、DEBUG、INFO、WARNING、ERROR、FITAL、OFF
		 * 比設定級別低的訊息將不會被記錄
		 */
		log.trace("trace info...");
		// 在log4j.properties設定的級別是ERROR，所以如果是WARNING以下的級別都會直接return，不會消耗太多資源
		if (log.isEnabledFor(Priority.DEBUG)) {
			// 但是串接字串會在return之前消耗額外時間，所以最好用isEnabledFor()或者isDebugEnabled()來檢查
			log.debug("debug info..." + ss);
		}
		log.info("info info...");
		log.warn("warn info...");
		log.error("error info...");		
		log.fatal("fatal info...");
		
		try {
			String s = null;
			s.length();
		} catch (Exception e) {
			log.trace("trace 一個例外", e);
			log.debug("debug 一個例外", e);
			log.info("info 一個例外", e);
			log.warn("warn 一個例外", e);
			log.error("error 一個例外", e);
			log.fatal("fatal 一個例外", e);
		}
	}
}
