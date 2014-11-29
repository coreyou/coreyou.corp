package datesAndTimes;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * 今日時間
 * 
 * @author coreyou
 *
 */
public class todayDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 印出今日時間的兩種方法
		Date date = new Date();
		System.out.println(date);	// Mon Jul 21 07:45:17 CST 2014
		System.out.println(Calendar.getInstance().getTime());	// 雖然是getTime()，可是不是回傳Time物件，而是回傳Date物件。
		
		// Date().getTime()是秒數，然而Calendar.getTime()是Date
		System.out.println(new java.util.Date().getTime());	// 1405899917571
		
		DateFormat df = DateFormat.getDateTimeInstance();
		DateFormat df2 = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		DateFormat df3 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		System.out.println(df.format(date));	// 2014/7/21 上午 07:45:17
		System.out.println(df2.format(date));	// 2014年7月21日 上午07時45分17秒
		System.out.println(df3.format(date));	// 2014/7/21 上午 7:45
		
		Calendar cal = new GregorianCalendar();
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH) + 1;	// 0~11
		int dOfM = cal.get(cal.DAY_OF_MONTH);
		int dOfW = cal.get(cal.DAY_OF_WEEK);
		int wOfM = cal.get(cal.WEEK_OF_MONTH);
		int hOfD = cal.get(cal.HOUR_OF_DAY);
		int hour = cal.get(cal.HOUR);
		int am_pm = cal.get(cal.AM_PM);
		String strAmPm = (am_pm == 0) ? "am" : "pm";
		System.out.println("今年: " + year + "年, 本月: " + month + "月, 今天: " + dOfM + "號, 今天是本周第: " + dOfW + "天, 本月第: " + wOfM + "周, 現在時刻: " + hOfD + "點, 另一種表示法: " + hour + strAmPm);
	}

}
