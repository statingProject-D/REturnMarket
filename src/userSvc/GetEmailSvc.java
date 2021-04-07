package userSvc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.UserDAO;

public class GetEmailSvc {
	public String getUserEmail(String user_id) {
		String email = null;
		Connection con = null;
		
		try {
			con = getConnection();
			UserDAO userDao = UserDAO.getInstance();
			userDao.setConnection(con);
			
			email = userDao.getUserEmail(user_id);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) { con.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
				
		return email;
	}
}
