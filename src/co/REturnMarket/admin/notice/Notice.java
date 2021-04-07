package co.REturnMarket.admin.notice;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.notice.dao.NoticeDAO;
import co.REturnMarket.admin.notice.vo.NoticeVo;

public class Notice implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("id") == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인 이후 이용가능 합니다.');");
			script.println("location.href='login.ad';");
			script.println("</script>");
			script.close();
		}
		
		NoticeDAO noticeDao = new NoticeDAO();
		ArrayList<NoticeVo> voList = noticeDao.selectNotice();
		
		if(voList != null) {
			request.setAttribute("voList", voList);
		}
		
		request.setAttribute("page", "/WEB-INF/views/notice/noticeInfo.jsp");
		
		return "main/main.jsp";
	}
}
