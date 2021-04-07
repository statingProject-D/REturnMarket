package boardSvc;

import java.sql.Connection;
import dao.BoardDAO;
import static db.JdbcUtil.*;
import vo.CmtBean;

public class UploadCmtSvc {
	public boolean uploadCmt(CmtBean cmtBean) {
		boolean uploadCmt = false;
		Connection con = null;
		
		try {
			con = getConnection();
			BoardDAO boardDao = BoardDAO.getInstance();
			boardDao.setConnection(con);
			
			int uploadSuccess = boardDao.uploadComment(cmtBean);

			if(uploadSuccess > 0) {
				uploadCmt = true;
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(con != null) con.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return uploadCmt;
	}
}
