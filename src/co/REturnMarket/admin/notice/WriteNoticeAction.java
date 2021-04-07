package co.REturnMarket.admin.notice;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.notice.dao.NoticeDAO;
import co.REturnMarket.admin.notice.vo.NoticeVo;

public class WriteNoticeAction implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id = request.getParameter("user_id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		NoticeDAO noticeDao = new NoticeDAO();
		NoticeVo vo = new NoticeVo();
		
		vo.setUser_id(user_id);
		vo.setTitle(title);
		vo.setContent(content);
		
		int uploadSuccess = noticeDao.uploadNotice(vo);
		
		if(uploadSuccess < 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('공지사항을 등록에 실패했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		return "notice.ad";
	}
}
