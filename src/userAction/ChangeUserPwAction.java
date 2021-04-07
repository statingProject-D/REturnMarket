package userAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import security.SHA512SecureUtil;
import userSvc.ChangeUserPwSvc;
import vo.ActionForward;
import vo.UserBean;

public class ChangeUserPwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String user_id = null;
		
		if(session.getAttribute("user_id") != null) {
			user_id = (String)session.getAttribute("user_id");
		}
		
		if(user_id == null) { // session이 존재 하지 않을경우
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('사용자 정보가 존재하지 않습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			
			String pw = request.getParameter("pw").trim();
			String hashPw = SHA512SecureUtil.SHA256(pw);
			
			System.out.println(pw);
			
			String rePw = request.getParameter("rePw").trim();
			String hashRePw = SHA512SecureUtil.SHA256(rePw);
			
			System.out.println(rePw);
			
			if(!pw.equals(rePw)) { // 비밀번호가 일치 하지 않았을 경우
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('비밀번호가 일치하지 않습니다.');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
			} else {
				UserBean userBean = new UserBean();

				userBean.setUser_id(user_id);
				userBean.setPw(hashPw);
				userBean.setRe_pw(hashRePw);
				
				ChangeUserPwSvc changeUserPwSvc = new ChangeUserPwSvc();
				boolean isPwChangeSuccess = changeUserPwSvc.changePwUser(userBean);
				
				if(isPwChangeSuccess) { // 수정 성공
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('비밀번호를 성공적으로 변경했습니다.');");
					script.println("location.href='login.us';");
					script.println("</script>");
					script.close();
				} else { // 수정 실패
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('비밀번호변경에 실패 하셨습니다.');");
					script.println("history.back();");
					script.println("</script>");
					script.close();
				}
			}
		}
		
		return forward;
	}
}
