package pdSvc;

import java.sql.Connection;
import dao.PdDAO;
import vo.BidBean;
import vo.UserBean;

import static db.JdbcUtil.*;

public class GetBuyerInfoSvc {
	public BidBean getBuyerBidInfo(int pdNum) {
		BidBean bidBean = null;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			bidBean = pdDao.getBuyerBidInfo(pdNum);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return bidBean;
	}

	public UserBean getBuyerUserInfo(BidBean bidBean) {
		UserBean userBean = null;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			userBean = pdDao.getBuyerUserInfo(bidBean);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return userBean;
	}
}
