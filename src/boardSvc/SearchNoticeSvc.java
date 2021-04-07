package boardSvc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import static db.JdbcUtil.*;
import vo.NoticeBean;

public class SearchNoticeSvc {
	public ArrayList<NoticeBean> searchInNotice() {
		ArrayList<NoticeBean> noticeList = null;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);
			
			noticeList = boardDao.getNoticeList();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) {e.printStackTrace(); }
		}
		
		return noticeList;
	}
}
