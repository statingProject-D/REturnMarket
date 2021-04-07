package pdSvc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.PdDAO;
import static db.JdbcUtil.*;
import vo.BidBean;

public class BidUploadSvc {
	public boolean uploadBid(BidBean bidBean) {
		boolean isBidUpload = false;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			int bidSuccess = pdDao.bidUpload(bidBean);
			int finalPrice = pdDao.finalPrice(bidBean);
			
			if(bidSuccess > 0 && finalPrice > 0) {
				commit(con);
				isBidUpload = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}

		return isBidUpload;
	}

	public ArrayList<BidBean> getBidList(BidBean bidBean) {
		ArrayList<BidBean> bidList = null; 
		Connection con = null;
				
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			bidList = pdDao.getBidList(bidBean);

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return bidList;
	}

	public static String chkLastBider(BidBean bidBean) {
		String chkLastBider = null;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			String lastBider = pdDao.bidLastUser(bidBean);
			
			if(lastBider == null) {
				return lastBider = " ";
			} else {
				return lastBider;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return chkLastBider;
	}
}
