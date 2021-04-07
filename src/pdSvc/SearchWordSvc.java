package pdSvc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.PdDAO;

import static db.JdbcUtil.*;
import vo.PdBean;

public class SearchWordSvc {
	public ArrayList<PdBean> searchWord(String bgCg, String smCg) {
		ArrayList<PdBean> searchWordList = null;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			searchWordList = pdDao.searchCategory(bgCg, smCg);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return searchWordList;
	}
	
	public ArrayList<PdBean> pdNameSearch(String searchPdName) {
		ArrayList<PdBean> pdNameSearch = null;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			pdNameSearch = pdDao.pdNameSearch(searchPdName);
					
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return pdNameSearch;
	}

	public ArrayList<PdBean> totalSearch(String bgCg, String smCg, String searchPdName) {
		ArrayList<PdBean> totalSearch = null;
		Connection con = null;
		
		try {
			con = getConnection();
			PdDAO pdDao = PdDAO.getInstance();
			pdDao.setConnection(con);
			
			totalSearch = pdDao.totalSearch(bgCg, smCg, searchPdName);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return totalSearch;
	}
}
