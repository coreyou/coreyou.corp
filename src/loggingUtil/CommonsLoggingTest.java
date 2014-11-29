package loggingUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;;
/**
 * 3.使用Apache Commons Logging來記錄log，
 * 要記得先在/src下建立commons-logging.properties的設定檔，
 * Build Path > Configure Build Path > Libraries > Add External JARs.. > 找到commons-logging-1.2.jar。
 * 
 * Commons Logging可以選擇要使用JDK Loggin還是Log4j，
 * 預設上，如果有Log4j，commons-loggin會交給log4j，若否，則會將相對應的輸出轉為JDK logging。
 * 
 * @author coreyou
 *
 */
public class CommonsLoggingTest {
	public static Log log = LogFactory.getLog(CommonsLoggingTest.class);
	
	public static void main(String[] args) {
		log.trace("trace info...");
		log.debug("debug info...");
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
