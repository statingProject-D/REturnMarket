package boardSvc;

import java.sql.Connection;
import dao.BoardDAO;

import static db.JdbcUtil.*;

public class DelQnaSvc {
	public boolean deleteQna(int no) {
		boolean delQna = false;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);
			
			int deleteSuccess = boardDao.deleteQna(no);
			
			if(deleteSuccess > 0) { // 글 삭제
				boardDao.deletComment(no); // 댓글 무조건삭제
				commit(con);
				delQna = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return delQna;
	}
}
