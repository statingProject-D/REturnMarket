package userAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import userSvc.MatchingUserInfoSvc;
import userSvc.UserIdFindSvc;
import vo.ActionForward;
import vo.UserBean;

public class FindUserInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String user_id = request.getParameter("user_id").trim();
		String name = request.getParameter("name").trim();
		String phone = request.getParameter("phone").trim();
		String email = request.getParameter("email").trim();

		if(!user_id.equals(" ") && name != null && phone != null && email != null) { // 아이디 찾기			
			UserBean userBean = new UserBean();
			
			userBean.setUser_id(user_id);
			userBean.setName(name);
			userBean.setPhone(phone);
			userBean.setEmail(email);
			
			MatchingUserInfoSvc matchingUserInfoSvc = new MatchingUserInfoSvc();
			boolean matchUserSuccess = matchingUserInfoSvc.matchingUser(userBean);
			
			if(matchUserSuccess == true) { // 정보가 일치할경우 비밀번호 변경 page로 이동
				HttpSession session = request.getSession();
//				session.setAttribute("userBean", userBean);
				session.setAttribute("user_id", user_id);
				
				forward = new ActionForward();
				forward.setPath("userPwChange.us");
				
			} else if(name != null && phone != null && email != null) {
			
				userBean.setName(name);
				userBean.setPhone(phone);
				userBean.setEmail(email);
				
				UserIdFindSvc userIdFindSvc = new UserIdFindSvc();
				String getUserId = userIdFindSvc.findUserId(userBean);
				
				if(getUserId != null) { // 아이디 값을 찾았을 경우
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('회원님의 아이디는 " +getUserId +" 입니다.');");
					script.println("location.href='login.us';");
					script.println("</script>");
					script.close();
				} else { // id 값 가져오기 실패
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('아이디를 불러오는데 실패했습니다.');");
					script.println("history.back();");
					script.println("</script>");
					script.close();
				}
				
			} else { // 정보 불러오기가 불일치 할경우
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('정보를 다시 확인해주세요!');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
			}
		} 
		
		return forward;
	}

}
