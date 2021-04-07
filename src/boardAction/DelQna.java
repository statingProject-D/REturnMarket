package boardAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import boardSvc.DelQnaSvc;
import vo.ActionForward;

public class DelQna implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		DelQnaSvc delQnaSvc = new DelQnaSvc();
		boolean delQnaSuccess = delQnaSvc.deleteQna(no);
		
		if(!delQnaSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('삭제에 실패 했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			forward = new ActionForward();
			forward.setPath("qnaBoard.bo");
		}
		
		return forward;
	}
}
