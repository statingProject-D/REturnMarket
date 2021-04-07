package pdAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import pdSvc.DelJjimSvc;
import vo.ActionForward;
import vo.JjimBean;

public class DelJjimAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		int pdNum = Integer.parseInt(request.getParameter("pdNum"));
		String user_id = (String)session.getAttribute("user_id");
		
		DelJjimSvc delJjimSvc = new DelJjimSvc();
		boolean deleteSuccess = delJjimSvc.deleteJjim(pdNum, user_id);
		
		if(deleteSuccess) {
			forward = new ActionForward();
			forward.setPath("jjimList.pd");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('삭제에 실패했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		return forward;
	}
}
