package userSvc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.UserDAO;

public class LoginUserSvc {

	public boolean loginUser(String user_id, String hashpw) {
		boolean loginUser = false;
		Connection con = null;
		
		try {
			con = getConnection();
			UserDAO userDao = UserDAO.getInstance();
			userDao.setConnection(con);
			
			boolean loginSuccess = userDao.login(user_id, hashpw);
			
			if(loginSuccess) {
				commit(con);
				loginUser = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) { con.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		
		return loginUser;
	}
}
