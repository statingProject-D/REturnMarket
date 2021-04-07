package userSvc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.UserDAO;

public class DeleteUserSvc {

	public boolean deleteUser(String user_id, String hashpw) {
		boolean deleteId = false;
		Connection con = null;
		
		try {
			con = getConnection();
			UserDAO userDao = UserDAO.getInstance();
			userDao.setConnection(con);
			
			boolean deleteSuccess = userDao.deleteUser(user_id, hashpw);
			
			if(deleteSuccess) {
				commit(con);
				deleteId = true;
				
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) { con.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		
		return deleteId;
	}
}
