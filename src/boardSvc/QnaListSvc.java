package boardSvc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.QnaBean;

public class QnaListSvc {
	public int getListCount() {
		int listCount = 0;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);
			
			listCount = boardDao.selectAllQnaCount();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); }catch(Exception e) { e.printStackTrace(); }
		}
		
		return listCount;
	}

	public ArrayList<QnaBean> getQnaList(int page, int limit) {
		ArrayList<QnaBean> qnaList = null;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);
			
			qnaList = boardDao.selectAllQnaList(page, limit);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); }catch(Exception e) { e.printStackTrace(); }
		}
		
		return qnaList;
	}

	public ArrayList<QnaBean> getQnaSearchWord(int page, int limit, String searchWord) {
		ArrayList<QnaBean> qnaList = null;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);

			qnaList = boardDao.getQnaSearchWord(page, limit, searchWord);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return qnaList;
	}
}
