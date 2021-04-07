package boardSvc;

import java.sql.Connection;
import dao.BoardDAO;
import static db.JdbcUtil.*;
import vo.QnaBean;

public class ModiQnaSvc {
	public boolean modifyQna(QnaBean qnaBean) {
		boolean modiQna = false;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);
			
			int updateSuccess = boardDao.updateQna(qnaBean);
			
			if(updateSuccess > 0) {
				commit(con);
				modiQna = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) {e.printStackTrace(); }
		}
		
		return modiQna;
	}
}
