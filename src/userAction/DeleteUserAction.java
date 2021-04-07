package userAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import security.SHA512SecureUtil;
import userSvc.DeleteUserSvc;
import vo.ActionForward;

public class DeleteUserAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String session_id = null;
		
		String pw = request.getParameter("pw").trim();
		String hashpw = SHA512SecureUtil.SHA256(pw);
		
		if(session.getAttribute("user_id") != session_id || session.getAttribute("user_id") != null) {
			session_id =  (String)session.getAttribute("user_id");
		}
		
		if(session_id == null || session_id == " ") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인이 필요한 항목 입니다.');");
			script.println("location.href='login.us';");
			script.println("</script>");
			script.close();
		}
		
		DeleteUserSvc deleteUserSvc = new DeleteUserSvc();
		boolean deleteSuccess = deleteUserSvc.deleteUser(session_id, hashpw);
		
		if(deleteSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('탈퇴에 실패하셨습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			session.invalidate();			
			script.println("<script>");
			script.println("alert('그동안 이용해 주셔서 감사합니다.');");
			script.println("location.href='home.us';");
			script.println("</script>");
			script.close();
		}
		
		forward = new ActionForward();
		forward.setPath("home.us");
		
		return forward;
	}
}
