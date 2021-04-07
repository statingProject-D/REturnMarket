package boardAction;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import boardSvc.QnaViewSvc;
import vo.ActionForward;
import vo.CmtBean;
import vo.QnaBean;

public class QnaView implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		if(no == 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('QnA를 읽는데 문제가 발생했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			QnaViewSvc qnaViewSvc = new QnaViewSvc();
			QnaBean qnaBean = qnaViewSvc.getQnaInfo(no);
			ArrayList<CmtBean> cmtList = qnaViewSvc.getCmtBean(no);
			
			if(qnaBean == null) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('QnA를 불러오는데 문제가 발생했습니다.');");
				script.println("history.back();");
				script.println("</script>");
			} else {
				forward = new ActionForward();
				request.setAttribute("qnaBean", qnaBean);
				request.setAttribute("cmtList", cmtList);
				
				forward.setPath("jsp/board/qnaView.jsp");
			}
		}
		
		return forward;
	}
}
