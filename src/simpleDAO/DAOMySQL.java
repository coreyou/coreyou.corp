package simpleDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOMySQL extends DAOFactory {

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			// 註冊driver
			Class.forName("com.mysql.jdbc.Driver");
			// 取得connection
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/coreyou_corp?useUnicode=true&characterEncoding=Big5",
					"coreyou","751201267");		      
	    } catch (ClassNotFoundException e) {
		      System.out.println("DriverClassNotFound :"+e.toString());
	    } catch (SQLException x) {
		      System.out.println("Exception :"+x.toString());
	    }
		return con;
	}

}
