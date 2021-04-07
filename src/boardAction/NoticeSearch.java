package boardAction;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import boardSvc.GetNoticeSvc;
import boardSvc.NoticeListSvc;
import vo.ActionForward;
import vo.NoticeBean;
import vo.PageInfo;

public class NoticeSearch implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String searchWord = request.getParameter("searchWord");
		
		GetNoticeSvc getNoticeSvc = new GetNoticeSvc();
		ArrayList<NoticeBean> noticeList = getNoticeSvc.searchNotice(searchWord);
		
		if(noticeList == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('검색어에 응하는 게시물이 없습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			forward = new ActionForward();
			session.setAttribute("noticeList", noticeList);
			forward.setPath("noticeBoardResult.bo");
		}
		
		return forward;
	}
}
