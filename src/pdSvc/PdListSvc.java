package pdSvc;

import java.sql.Connection;
import java.util.ArrayList;
import dao.PdDAO;
import static db.JdbcUtil.*;
import vo.PdBean;

public class PdListSvc {
	public static ArrayList<PdBean> getPdList() {
		ArrayList<PdBean> pdList = null;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			pdList = pdDao.getPdList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return pdList;
	}
}
