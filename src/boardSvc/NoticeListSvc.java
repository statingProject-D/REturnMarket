package boardSvc;

import java.sql.Connection;
import java.util.ArrayList;
import dao.BoardDAO;
import static db.JdbcUtil.*;
import vo.NoticeBean;

public class NoticeListSvc {
	public int getListCount() {
		int listCount = 0;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);
			
			listCount = boardDao.selectAllNoticeCount(); // 1이면 성공
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); }catch(Exception e) { e.printStackTrace(); }
		}
		
		return listCount; // 0 이면 실패
	}

	public ArrayList<NoticeBean> getNoticeList(int page, int limit) {
		ArrayList<NoticeBean> list = null;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);
			list = boardDao.selectAllNoticeList(page, limit);			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); }catch(Exception e) { e.printStackTrace(); }
		}
		
		return list;
	}

}
