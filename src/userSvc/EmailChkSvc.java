package userSvc;

import java.sql.Connection;

import dao.UserDAO;

import static db.JdbcUtil.*;

public class EmailChkSvc {
	public boolean emailchked(String user_id) {
		boolean emailChk = false;
		Connection con = null;
		
		try {
			con = getConnection();
			UserDAO userDao = UserDAO.getInstance();
			userDao.setConnection(con);
			
			boolean emailSendChk = userDao.getUserEmailChecked(user_id);
			
			if(emailSendChk == true) {
				emailChk = true;
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) { con.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		
		return emailChk;
	}

}
