package boardAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import boardSvc.NoticeViewSvc;
import vo.ActionForward;
import vo.NoticeBean;

public class NoticeView implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		if(no == 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('공지사항을 읽는데 문제가 발생했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			NoticeViewSvc noticeViewSvc = new NoticeViewSvc();
			NoticeBean noticeBean = noticeViewSvc.getNoticeInfo(no);
			
			if(noticeBean == null) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('공지사항을 불러오는데 문제가 발생했습니다.');");
				script.println("history.back();");
				script.println("</script>");
			} else {
				forward = new ActionForward();
				session.setAttribute("noticeBean", noticeBean);
				
				forward.setPath("jsp/board/noticeView.jsp");
			}
		}
		
		return forward;
	}
}
