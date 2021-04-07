package userAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import security.SHA256;
import userSvc.GetEmailSvc;
import userSvc.SetEmailChkSvc;
import vo.ActionForward;

public class EmailChkProAciton implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession(true);
		
		request.setCharacterEncoding("UTF-8");
		
		String user_id = request.getParameter("user_id").trim();
		String code = request.getParameter("code");
		
		String session_id = null;
		
		if(session.getAttribute("user_id") != null) {
			session_id = (String)session.getAttribute("user_id");
		}
		
		if(session_id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이메일 인증 후 로그인을 해주세요');");
			script.println("location.href='login.us';");
			script.println("</script>");
			script.close();
		}
		
		// 유저 이메일 가져오는 svc
		GetEmailSvc getEmailSvc = new GetEmailSvc();
		String getUserEmail = getEmailSvc.getUserEmail(user_id);
		
		// code값 확인
		boolean rightCode = (new SHA256().getSHA256(getUserEmail).equals(code)) ? true : false;
		
		// 유저 이메일 인증 값을 변경하는 svc
		SetEmailChkSvc setEmailChkSvc = new SetEmailChkSvc();
		boolean setUserEmailChkSuccess = setEmailChkSvc.setUserEmailChk(user_id);

		if(rightCode == true) {
			if(setUserEmailChkSuccess == true) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이메일 인증에 성공 하셨습니다.');");
				script.println("location.href='home.us';");
				script.println("</script>");
				script.close();
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이메일 인증에 실패 하셨습니다.');");
				script.println("location.href='login.us';");
				script.println("</script>");
				script.close();
			}
			
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 코드입니다.');");
			script.println("location.href = 'home.us';");
			script.println("</script>");
			script.close();
		}
		
		return forward;
	}

}
