package pdSvc;

import java.sql.Connection;
import dao.PdDAO;
import static db.JdbcUtil.*;
import vo.PdBean;

public class UploadPdSvc {
	public boolean uploadPd(PdBean pdBean) {
		boolean isUploadPd = false;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			int uploadPd = pdDao.uploadPd(pdBean);
			
			if(uploadPd > 0) {
				commit(con);
				isUploadPd = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) {e.printStackTrace(); }
		}
		
		
		return isUploadPd;
	}
}
