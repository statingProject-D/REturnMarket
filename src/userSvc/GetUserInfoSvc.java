package userSvc;

import java.sql.Connection;

import dao.UserDAO;
import vo.UserBean;

import static db.JdbcUtil.*;

public class GetUserInfoSvc {
	public static UserBean getUserInfo(String requester) {
		UserBean userBean = null;
		Connection con = null;
		
		try {
			con = getConnection();
			UserDAO userDao = UserDAO.getInstance();
			userDao.setConnection(con);
			
			userBean = userDao.getUserInfo(requester);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) { con.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		
		return userBean;
	}
}
