package co.REturnMarket.admin.notice;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.notice.dao.NoticeDAO;

public class NoticeDel implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeDAO noticeDao = new NoticeDAO();
		int deleteSuccess = noticeDao.deleteNotice(no);
		
		if(deleteSuccess < 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('공지사항을 삭제하는데 실패했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		return "notice.ad";
	}
}
