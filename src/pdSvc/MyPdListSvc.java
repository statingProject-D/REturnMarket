package pdSvc;

import java.sql.Connection;
import java.util.ArrayList;
import dao.PdDAO;
import static db.JdbcUtil.*;
import vo.PdBean;

public class MyPdListSvc {
	public ArrayList<PdBean> getMyPdList(String user_id) {
		ArrayList<PdBean> myPdList = null;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			myPdList = pdDao.getMyPdList(user_id);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return myPdList;
	}
}
