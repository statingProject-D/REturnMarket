package userSvc;

import java.sql.Connection;
import dao.UserDAO;
import static db.JdbcUtil.*;
import vo.UserBean;

public class ChangeUserPwSvc {
	public boolean changePwUser(UserBean userBean) {
		boolean changePw = false;
		Connection con = null;
		
		try {
			con = getConnection();
			UserDAO userDao = UserDAO.getInstance();
			userDao.setConnection(con);
			
			boolean ChangeUserPwSuccess = userDao.changeUserPw(userBean);
			
			if(ChangeUserPwSuccess) {
				commit(con);
				changePw = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) { con.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		
		
		
		
		return changePw;
	}
}
