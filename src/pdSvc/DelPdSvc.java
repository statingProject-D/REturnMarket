package pdSvc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.PdDAO;

public class DelPdSvc {
	public boolean deletePd(int pdNum) {
		boolean deletePd = false;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			int deletePdSuccess = pdDao.deletePd(pdNum);
			
			if(deletePdSuccess > 0) {
				commit(con);
				deletePd = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) {e.printStackTrace(); }
		}
		
		
		return deletePd;
	}

	public boolean deleteDirectList(int pdNum, String requester) {
		boolean deleteDirectList = false;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			int delDirectList = pdDao.delDirectList(pdNum, requester);
			
			if(delDirectList > 0) {
				commit(con);
				deleteDirectList = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) {e.printStackTrace(); }
		}
		
		return deleteDirectList;
	}
}
