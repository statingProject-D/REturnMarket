package boardSvc;

import java.sql.Connection;

import dao.BoardDAO;

import static db.JdbcUtil.*;
import vo.QnaBean;

public class UploadQnaSvc {
	public boolean uploadQna(QnaBean qnaBean) {
		boolean uploadQna = false;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);
			
			int successUpload = boardDao.uploadQna(qnaBean);
			
			if(successUpload > 0) {
				commit(con);
				uploadQna = true;
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return uploadQna;
	}
}
