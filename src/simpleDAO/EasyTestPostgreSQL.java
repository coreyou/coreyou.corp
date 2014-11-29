package simpleDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 要記得在本檔按右鍵 > Build Path > Configure Build Path > Libraries > Add External JARs > 找到Tomecat下的lib中的postgresql JAR檔
 * 
 * @author coreyou
 *
 */
public class EasyTestPostgreSQL {
	private static Connection con = null;	// 一個Connection物件下，可以建立一個以上的Statement物件
	private static Statement stat = null;	// 一個Statement物件下，只能建立一個ResultSet物件
	private static ResultSet rs = null;		// 同時間同一個Statement物件下，只能開啟一個ResultSet物件，新開一個，前一個會被關掉
	private static String TABLE_NAME = "userTest";
	private static String URL = "jdbc:postgresql://localhost:5432/coreyou_corp?charSet=UTF-8";
	private static String USER = "coreyou";
	private static String PASSWORD = "751201267";
	
	private void connect() {
		try {
			// 註冊driver
			Class.forName("org.postgresql.Driver");
			// 取得connection
			con = DriverManager.getConnection(URL, USER, PASSWORD);
	    } catch (ClassNotFoundException e) {
		      System.out.println("DriverClassNotFound :" + e.toString());
	    } catch (SQLException x) {
		      System.out.println("Exception :" + x.toString());
	    }
	}
	
	private void select() {
		if (con != null) {
			try {
				stat = con.createStatement();
				rs = stat.executeQuery("select * from \"" + TABLE_NAME + "\"");	// PostgreSQL會直接把TABLE_NAME轉成小寫，所以要用引號框起來
				
				printTable(rs, "select table");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	private void insert() {
		if (con != null) {
			try {
				/* 
				 * ResultSet.TYPE_SCROLL_INSENSITIVE: 表示紀錄是可以捲動的，若有其他人更動了紀錄的內容，並不會同步更新到我們產生的Result物件
				 * ResultSet.CONCUR_UPDATABLE: 表示ResultSet物件是可以被更改的
				 */
				stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = stat.executeQuery("select * from \"" + TABLE_NAME + "\"");
				
				rs.moveToInsertRow();
				rs.updateString(1, "2");
				rs.updateString(2, "Tom");
				rs.updateString(3, "tom@msn.com");
				rs.insertRow();
				
				printTable(rs, "insert a row");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	private void update() {
		if (con != null) {
			try {
				/* 
				 * ResultSet.TYPE_SCROLL_INSENSITIVE: 表示紀錄是可以捲動的，若有其他人更動了紀錄的內容，並不會同步更新到我們產生的Result物件
				 * ResultSet.CONCUR_UPDATABLE: 表示ResultSet物件是可以被更改的
				 */
				stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = stat.executeQuery("select * from \"" + TABLE_NAME + "\" where \"userId\"='2'");
				
				if (rs.next()) {
					rs.updateString(3, "tom@yahoo.com.tw");
					rs.updateRow();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	private void delete() {
		// TODO Auto-generated method stub
		if (con != null) {
			try {
				stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = stat.executeQuery("select * from \"" + TABLE_NAME + "\" where \"userId\"='2'");
				
				if (rs.next()) {
					rs.deleteRow();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void printTable(ResultSet rs, String header) {
		try {
			System.out.println("==================== " + header + " ====================");
			while (rs.next()) {
				System.out.println(rs.getString("userId") + "\t\t" +
			        rs.getString("userName") + "\t\t" + rs.getString("email"));
			}
			System.out.println("======================================================");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void close() {
		try {			
			stat.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		EasyTestPostgreSQL mysql = new EasyTestPostgreSQL(); 
		
		mysql.connect();
		mysql.select();
		mysql.insert();
		mysql.select();
		mysql.update();
		mysql.select();
		mysql.delete();	
		mysql.select();
		
		mysql.close();
	}

	
}
