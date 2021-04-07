package userAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import userSvc.GetUserInfoSvc;
import vo.ActionForward;
import vo.UserBean;

public class UserDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");
		
		GetUserInfoSvc getUserInfoSvc = new GetUserInfoSvc();
		UserBean userBean = getUserInfoSvc.getUserInfo(user_id);
		
		if(user_id == null || user_id == " ") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인이 필요한 항목 입니다.');");
			script.println("location.href='login.us';");
			script.println("</script>");
			script.close();
		} else {
			if(userBean != null) {
				request.setAttribute("user_id", user_id);
				request.setAttribute("userBean", userBean);
					
				forward = new ActionForward();
				forward.setPath("userDetailAction.us");
			} else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원정보가 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		return forward;
	}
}
