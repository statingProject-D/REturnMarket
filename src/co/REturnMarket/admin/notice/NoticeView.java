package co.REturnMarket.admin.notice;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.notice.dao.NoticeDAO;
import co.REturnMarket.admin.notice.vo.NoticeVo;

public class NoticeView implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeDAO noticeDao = new NoticeDAO();
		NoticeVo vo = noticeDao.getNotice(no);
		
		if(vo == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('공지사항을 불러오는데 실패하였습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		request.setAttribute("vo", vo);
		request.setAttribute("page", "/WEB-INF/views/notice/noticeView.jsp");
		
		return "main/main.jsp";
	}
}
