package userAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import security.SHA512SecureUtil;
import userSvc.EmailChkSvc;
import userSvc.LoginUserSvc;
import vo.ActionForward;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession(); // session 객체 생성
		
		request.setCharacterEncoding("UTF-8");
		
		String user_id = request.getParameter("user_id").trim();
		String pw = request.getParameter("pw").trim();
		String hashpw = SHA512SecureUtil.SHA256(pw);
		
		if(user_id == null || pw == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디와 비밀번호를 다시 입력해 주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}

		// 아이디 비밀번호 같은지 확인
		LoginUserSvc loginUserSvc = new LoginUserSvc();
		boolean loginSuccess = loginUserSvc.loginUser(user_id, hashpw);
		
		EmailChkSvc emailChkSvc = new EmailChkSvc();
		boolean emailChkSuccess = emailChkSvc.emailchked(user_id);
		
		if(loginSuccess == true) { // 아이디 비번 값이 db와 같을경우
			if(emailChkSuccess == true) { // 이메일 인증 됬을경우
				session.setAttribute("user_id", user_id);
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("home.us");
			} else { // 이메일 인증이 안된경우
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이메일 인증 확인 부탁드립니다.');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
			}
		} else { // 로그인 실패 경우
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재 하지 않는 아이디 이거나, 비밀번호를 다시 확인하세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
				
		return forward;
	}
}
