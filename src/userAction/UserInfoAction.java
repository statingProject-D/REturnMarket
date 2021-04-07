package userAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import userSvc.GetUserInfoSvc;
import vo.ActionForward;
import vo.UserBean;

public class UserInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String user_id = null;

		if(session.getAttribute("user_id") == null) {			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원 정보가 불확실 합니다. 다시 로그인해 주세요.');");
			script.println("location.href='login.us';");
			script.println("</script>");
			script.close();
		} else {
			user_id = (String)session.getAttribute("user_id");
			
			GetUserInfoSvc getUserinfoSvc = new GetUserInfoSvc();
			UserBean userBean = getUserinfoSvc.getUserInfo(user_id);
			
			if(userBean != null) {
				session.setAttribute("user_id", user_id);
				session.setAttribute("userBean", userBean);
				
				forward = new ActionForward();
				forward.setPath("jsp/user/userModify.jsp");
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('회원정보가 없습니다.');");
				script.println("location.href='login.us';");
				script.println("</script>");
				script.close();
			}
		}
		
		return forward;
	}
}
