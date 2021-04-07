package pdSvc;

import java.sql.Connection;

import dao.PdDAO;

import static db.JdbcUtil.*;
import vo.PdBean;

public class ModiPdSvc {
	public boolean modifyPd(PdBean pdBean) {
		boolean modifyPd = false;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			int successModi = pdDao.modiPd(pdBean);
			
			int deleteBid = pdDao.deleteBid(pdBean);
			
			if(successModi > 0 && deleteBid > 0) {
				commit(con);
				modifyPd = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		
		return modifyPd;
	}
}
