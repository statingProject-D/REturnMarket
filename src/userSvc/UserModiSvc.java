package userSvc;

import java.sql.Connection;
import dao.UserDAO;
import static db.JdbcUtil.*;
import vo.UserBean;

public class UserModiSvc {
	public boolean modifyUser(UserBean userBean) {
		boolean isModifySuccess = false;
		Connection con = null;
		
		try {
			con = getConnection();
			UserDAO userDao = UserDAO.getInstance();
			userDao.setConnection(con);
			
			int modifyUser = userDao.updateUserInfo(userBean);
			
			if(modifyUser > 0) {
				commit(con);
				isModifySuccess = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) { con.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
				
		
		return isModifySuccess;
	}
}
