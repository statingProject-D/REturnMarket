package boardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import boardSvc.QnaViewSvc;
import vo.ActionForward;
import vo.QnaBean;

public class ModiQna implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		QnaViewSvc qnaViewSvc = new QnaViewSvc();
		QnaBean qnaBean = qnaViewSvc.getQnaInfo(no);
		
		forward = new ActionForward();
		request.setAttribute("qnaBean", qnaBean);
		forward.setPath("jsp/board/modiQna.jsp");
		
		return forward;
	}
}
