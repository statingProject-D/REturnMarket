package userAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import security.SHA512SecureUtil;
import userSvc.JoinUserSvc;
import vo.ActionForward;
import vo.UserBean;

public class JoinUserAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		UserBean userBean =  new UserBean();
		
		// 받아온 값들
		String user_id = request.getParameter("user_id").trim();
		String pw = request.getParameter("pw").trim();
		String rePw = request.getParameter("rePw").trim();
		String name = request.getParameter("name").trim();
		String phone = request.getParameter("phone").trim(); 
		String email = request.getParameter("email").trim();
		int addrNum = Integer.parseInt(request.getParameter("addrNum").trim());
		String addr1 = request.getParameter("addr1").trim();
		String addr2 = request.getParameter("addr2").trim();
		
		// 빈값일때 리턴
		if(user_id == null || pw == null || rePw == null || name == null || phone == null || 
				email == null || addrNum == 0 || addr1 == null || addr2 == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('빈값은 다음으로 갈 수 없습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		// 암호화
		String hashpw = SHA512SecureUtil.SHA256(pw);
		String hashre_pw = SHA512SecureUtil.SHA256(rePw);
		
		if((pw.equals(rePw))) { // 비밀번호와 비밀번호 확인 값이 같을때
			// db에 저장
			userBean.setUser_id(user_id);
			userBean.setPw(hashpw);
			userBean.setRe_pw(hashre_pw);
			userBean.setName(name);
			userBean.setPhone(phone);
			userBean.setEmail(email);
			userBean.setGrade("user");
			userBean.setAddrNum(addrNum);
			userBean.setAddr1(addr1);
			userBean.setAddr2(addr2);
			
		} else { // 2개의 비밀번호 값이 다를때
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀 번호가 일치하지 않습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		JoinUserSvc joinUserSvc = new JoinUserSvc();
		boolean isJoinUserSuccess = joinUserSvc.joinUser(userBean);
		
		if(isJoinUserSuccess) {
			HttpSession session = request.getSession();
			session.setAttribute("user_id", user_id);
			forward = new ActionForward();
			forward.setPath("sendEmail.us");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원등록에 실패하셨습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		return forward;
	}
}
