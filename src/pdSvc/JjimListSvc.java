package pdSvc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.PdDAO;

import static db.JdbcUtil.*;
import vo.JjimBean;

public class JjimListSvc {
	public ArrayList<JjimBean> getJjimList(String user_id) {
		ArrayList<JjimBean> jjimList = null;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			jjimList = pdDao.getJjimList(user_id);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return jjimList;
	}
}
