package boardSvc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;

import static db.JdbcUtil.*;

import vo.CmtBean;
import vo.QnaBean;

public class QnaViewSvc {
	public QnaBean getQnaInfo(int no) {
		QnaBean qnaBean = null;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);
			
			int viewCountUp = boardDao.qnaViewCount(no);
			if(viewCountUp > 0) {
				qnaBean = boardDao.getQnaInfo(no);
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return qnaBean;
	}

	public ArrayList<CmtBean> getCmtBean(int no) {
		ArrayList<CmtBean> cmtList = null;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);
			
			cmtList = boardDao.getComments(no);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return cmtList;
	}
}
