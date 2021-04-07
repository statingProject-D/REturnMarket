package boardAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import boardSvc.ModiQnaSvc;
import vo.ActionForward;
import vo.QnaBean;

public class ModiQnaAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		int no = Integer.parseInt(request.getParameter("no"));
		String user_id = (String)session.getAttribute("user_id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		QnaBean qnaBean = new QnaBean();
		qnaBean.setNo(no);
		qnaBean.setTitle(title);
		qnaBean.setContent(content);
		
		ModiQnaSvc modiQnaSvc = new ModiQnaSvc();
		boolean modiQnaSuccess = modiQnaSvc.modifyQna(qnaBean);
		
		if(!modiQnaSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('수정에 실패했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			forward = new ActionForward();
			forward.setPath("qnaView.bo");
		}
		
		
		return forward;
	}
}
