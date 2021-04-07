package userAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String session_id = null;
		
		if(session.getAttribute("user_id") != session_id || session.getAttribute("user_id") != null) {
			session_id = (String)session.getAttribute("user_id");
		}
		
		try {
			session.invalidate(); //로그아웃
			
			forward = new ActionForward();
			forward.setPath("home.us");
		} catch(Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그아웃에 실패 하셨습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		return forward;
	}
}
