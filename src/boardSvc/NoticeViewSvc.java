package boardSvc;

import java.sql.Connection;

import dao.BoardDAO;

import static db.JdbcUtil.*;
import vo.NoticeBean;

public class NoticeViewSvc {
	public NoticeBean getNoticeInfo(int no) {
		NoticeBean noticeBean = null;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);
			
			noticeBean = boardDao.getNoticeBean(no);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) {e.printStackTrace(); }
		}
		
		return noticeBean;
	}
}
