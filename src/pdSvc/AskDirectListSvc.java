package pdSvc;

import java.sql.Connection;
import java.util.ArrayList;
import dao.PdDAO;
import static db.JdbcUtil.*;
import vo.AskDirectBean;
import vo.PdBean;

public class AskDirectListSvc {
	public ArrayList<AskDirectBean> getDirectList(PdBean pdBean, String user_id) {
		ArrayList<AskDirectBean> directList = null;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			directList = pdDao.getDirectList(pdBean, user_id);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return directList;
	}
}
