package pdSvc;

import java.sql.Connection;

import dao.PdDAO;

import static db.JdbcUtil.*;
import vo.JjimBean;

public class JjimAddSvc {
	public boolean addJjims(JjimBean jjimBean, int pdNum, String user_id) {
		boolean jjimAdd = false;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			String chkMultiJjim = pdDao.chkMultiJjim(user_id, pdNum);
			int addSuccess = pdDao.addJjim(jjimBean, user_id);
			
			if(chkMultiJjim.equals(user_id)) {
				rollback(con);
			} else {
				if(addSuccess > 0) {
					jjimAdd = true;
					commit(con);
				} else {
					rollback(con);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return jjimAdd;
	}
}
