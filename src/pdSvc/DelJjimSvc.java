package pdSvc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.PdDAO;

public class DelJjimSvc {
	public boolean deleteJjim(int pdNum, String user_id) {
		boolean deleteJjim = false;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			int successDel = pdDao.deleteJjim(pdNum, user_id);
			
			if(successDel > 0) {
				commit(con);
				deleteJjim = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return deleteJjim;
	}
}
