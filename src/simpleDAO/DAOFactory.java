package simpleDAO;

import java.sql.Connection;

public abstract class DAOFactory {

	public abstract Connection getConnection();

}
