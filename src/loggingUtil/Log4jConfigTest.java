package loggingUtil;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
/**
 * 設定讀取其他的properties檔。
 * 
 * @author coreyou
 *
 */
public class Log4jConfigTest {
	public Logger log = Logger.getLogger(Log4jConfigTest.class);
	
	public void test() {
		PropertyConfigurator.configure("src/log4jConfigTest.properties");	// log4jConfigTest.properties放在coreyou.corp下
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (log.isDebugEnabled()) {	// 如果debug可用
					log.debug("開始計算 " + i + " * " + j + " 次運算");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Log4jConfigTest().test();
	}
}
