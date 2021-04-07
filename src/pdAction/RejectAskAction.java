package pdAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import pdSvc.DelPdSvc;
import vo.ActionForward;

public class RejectAskAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String requester = request.getParameter("requester");
		int pdNum = Integer.parseInt(request.getParameter("pdNum"));
		
		DelPdSvc delPdSvc = new DelPdSvc();
		boolean delDirectList = delPdSvc.deleteDirectList(pdNum, requester);
		
		if(!delDirectList) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('삭제 실패했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			forward = new ActionForward();
			forward.setPath("directBuyList.pd");
		}
		
		return forward;
	}
}
