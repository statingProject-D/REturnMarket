package userSvc;

import java.sql.Connection;

import dao.UserDAO;

import static db.JdbcUtil.*;

public class SetEmailChkSvc {
	public boolean setUserEmailChk(String user_id) {
		boolean setEmailChk = false;
		Connection con = null;
		
		try {
			con = getConnection();
			UserDAO userDao = UserDAO.getInstance();
			userDao.setConnection(con);
			
			boolean changeEmailChked = userDao.setUserEmailChk(user_id);
			
			if(changeEmailChked == true) {
				commit(con);
				setEmailChk = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) { con.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
				
		
		return setEmailChk;
	}
}
