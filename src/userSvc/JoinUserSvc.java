package userSvc;

import java.sql.Connection;

import dao.UserDAO;

import static db.JdbcUtil.*;
import vo.UserBean;

public class JoinUserSvc {
	public boolean joinUser(UserBean userBean) {
		boolean isJoinUserSuccess = false;
		Connection con = null;
		
		try {
			con = getConnection();
			UserDAO userDao = UserDAO.getInstance();
			userDao.setConnection(con);
			
			int joinUser = userDao.join(userBean);
			
			if(joinUser > 0) {
				commit(con);
				isJoinUserSuccess = true;
			} else {
				rollback(con);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) { con.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		
		return isJoinUserSuccess;
	}

}
