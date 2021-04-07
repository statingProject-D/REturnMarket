package userSvc;

import java.sql.Connection;
import dao.UserDAO;
import static db.JdbcUtil.*;
import vo.UserBean;

public class MatchingUserInfoSvc {

	public boolean matchingUser(UserBean userBean) {
		boolean matchingUserInfo = false;
		Connection con = null;
		
		try {
			con = getConnection();
			UserDAO userDao = UserDAO.getInstance();
			userDao.setConnection(con);
			
			boolean matchingSuccess = userDao.matchingUserInfo(userBean);
			
			if(matchingSuccess == true) {
				commit(con);
				matchingUserInfo = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); }catch(Exception e) { e.printStackTrace(); }
		}
		
		return matchingUserInfo;
	}
}
