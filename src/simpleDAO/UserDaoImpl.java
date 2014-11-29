package simpleDAO;

import java.sql.*;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM User_Table WHERE username = ? AND password = ?";
//        Connection conn = new DAOFactory().getConnection(); 
//        try{
//            PreparedStatement pstmt = conn.prepareStatement();
//            pstmt.setInt(1, u.getId());
//            pstmt.setString(2, u.getUsername());
//            pstmt.setString(3, u.getPassword());
//            ResultSet result = pstmt.executeUpdate();
//            if(result.next())
//            {
//                 User u = new User();
//                 u.setId(result.getInt("id"));
//                 u.setUsername(username);
//                 u.setPassword(password);
//                 return u;
//            }
//        }catch(java.sql.SQLException e){}
//        finally{
//            conn.close();
//        }
        return null;
	}

	@Override
	public void register(User u) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO User_Table (id, username, password) VALUES (?,?,?)";
//        Connection conn = new DAOFactory().getConnection(); 
//        try{
//            PreparedStatement pstmt = conn.prepareStatement();
//            pstmt.setInt(1, u.getId());
//            pstmt.setString(2, u.getUsername());
//            pstmt.setString(3, u.getPassword());
//            pstmt.executeUpdate();
//            
//        }catch(java.sql.SQLException e){}
//        finally{
//            conn.close();
//        }
	}

	@Override
	public boolean checkUser(User u) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM User_Table WHERE username = ?";
//        Connection conn = new DAOFactory().getConnection(); 
//        try{
//            PreparedStatement pstmt = conn.prepareStatement();
//            pstmt.setString(1, u.getUsername());
//            ResultSet result = pstmt.executeUpdate();
//            if(result.next())  //已存在
//            {
//                 return true;  
//            }
//        }catch(java.sql.SQLException e){}
//        finally{
//            conn.close();
//        }
        return false;
	}

}
