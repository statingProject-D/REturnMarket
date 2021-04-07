package userSvc;

import java.sql.Connection;
import dao.UserDAO;
import static db.JdbcUtil.*;
import vo.UserBean;

public class UserIdFindSvc {

	public String findUserId(UserBean userBean) {
		String user_id = null;
		Connection con = null;
		
		try {
			con = getConnection();
			UserDAO userDao = UserDAO.getInstance();
			userDao.setConnection(con);
			
			user_id = userDao.getUserId(userBean);
					
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return user_id;
	}
}
