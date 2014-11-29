package simpleDAO;

public interface UserDao {
	//登入時，如果成功即回傳一個封裝後的 User 物件,
    public User login(String username, String password);
    //註冊
    public void register(User u);
    //檢查 User 是否存在
    public boolean checkUser(User u);	
}
