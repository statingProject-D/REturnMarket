package pdAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class GoUploadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String user_id = null;
		
		if(session.getAttribute("user_id") != user_id || session.getAttribute("user_id") != null) {
			user_id =  (String)session.getAttribute("user_id");
		}
		
		if(user_id == null || user_id == " ") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인이 필요한 항목 입니다.');");
			script.println("location.href='login.us';");
			script.println("</script>");
			script.close();
		} else {
			forward = new ActionForward();
			session.setAttribute("user_id", user_id);
			forward.setRedirect(true);
			forward.setPath("addProduct.pd");
		}
		
		return forward;
	}
}
