package userAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import security.SHA512SecureUtil;
import userSvc.UserModiSvc;
import vo.ActionForward;
import vo.UserBean;

public class UserInfoModyfiyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");
		
		if(session.getAttribute("user_id") == null) { // session이 존재 하지 않을경우
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('사용자 정보가 없습니다. 다시 로그인 해주세요.');");
			script.println("location.href='login.us';");
			script.println("</script>");
			script.close();
		} else { //session이 유지가 될 경우
			// 받은 비밀번호 hash값으로 변경
			String pw = request.getParameter("pw").trim();
			String hashpw = SHA512SecureUtil.SHA256(pw);
			String rePw = request.getParameter("rePw").trim();
			String hashRePw = SHA512SecureUtil.SHA256("rePw");
			
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
				userBean.setPw(hashpw);
				userBean.setRe_pw(hashRePw);
				userBean.setName(request.getParameter("name").trim());
				userBean.setPhone(request.getParameter("phone").trim());
				userBean.setEmail(request.getParameter("email").trim());
				userBean.setAddrNum(Integer.parseInt(request.getParameter("addrNum").trim()));
				userBean.setAddr1(request.getParameter("addr1").trim());
				userBean.setAddr2(request.getParameter("addr2").trim());
				userBean.setUser_id(user_id);
				
				UserModiSvc userModiSvc = new UserModiSvc();
				boolean isModifySuccess = userModiSvc.modifyUser(userBean);
				
				if(isModifySuccess) { // 수정 성공
					session.setAttribute("userBean", userBean);
					session.setAttribute("user_id", user_id);
					
					forward = new ActionForward();
					forward.setRedirect(true);
					forward.setPath("userDetail.us");
				} else {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('회원 정보 수정에 실패 하셨습니다.');");
					script.println("history.back();");
					script.println("</script>");
					script.close();
				}
			}
		}
		
		return forward;
	}
}
