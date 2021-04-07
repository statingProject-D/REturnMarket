package pdSvc;

import java.sql.Connection;
import dao.PdDAO;
import vo.AskDirectBean;

import static db.JdbcUtil.*;

public class AskDirectBuySvc {
	public boolean addAskDirectBuy(AskDirectBean askDirectBean) {
		boolean addAskDirectBuy = false;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			int insertAskDirect = pdDao.addAskDirect(askDirectBean);
			
			if(insertAskDirect > 0) {
				commit(con);
				addAskDirectBuy = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return addAskDirectBuy;
	}
}
