package boardSvc;

import java.sql.Connection;
import dao.BoardDAO;
import static db.JdbcUtil.*;

public class DeleteCmtSvc {
	public boolean deleteComment(int cmt_no) {
		boolean deleteCmt = false;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);
			
			int deleteSuccess = boardDao.deletAnyComment(cmt_no);
			
			if(deleteSuccess > 0) {
				deleteCmt = true;
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); }catch(Exception e) { e.printStackTrace(); }
		}
		
		return deleteCmt;
	}
}
