package pdSvc;

import java.sql.Connection;
import dao.PdDAO;
import static db.JdbcUtil.*;
import vo.PdBean;

public class PdViewSvc {

	public static PdBean getPdView(int pdNum) {
		PdBean pdBean = null;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			int viewCount = pdDao.upViewCount(pdNum);
			
			if(viewCount > 0) {
				commit(con);
				pdBean = pdDao.getPdView(pdNum);
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) {e.printStackTrace(); }
		}
		
		return pdBean;
	}
}