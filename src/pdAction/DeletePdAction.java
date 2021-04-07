package pdAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import pdSvc.DelPdSvc;
import vo.ActionForward;
import vo.PdBean;

public class DeletePdAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int pdNum = Integer.parseInt(request.getParameter("pdNum"));
		DelPdSvc delPdSvc = new DelPdSvc();
		boolean delPdSuccess = delPdSvc.deletePd(pdNum);
		
		if(!delPdSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('게시물 삭제에 문제가 발생했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			forward = new ActionForward();
			forward.setPath("productList.pd");
		}
		
		return forward;
	}
}
