package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import userAction.ChangeUserPwAction;
import userAction.DeleteUserAction;
import userAction.EmailChkProAciton;
import userAction.FindUserInfoAction;
import userAction.JoinUserAction;
import userAction.LoginAction;
import userAction.LogoutAction;
import userAction.SendEmailAction;
import userAction.UserDetailAction;
import userAction.UserInfoAction;
import userAction.UserInfoModyfiyAction;
import vo.ActionForward;

/**
 * Servlet implementation class UserController
 */
@WebServlet("*.us")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		ActionForward forward = null;
		Action action = null;
	
		System.out.println(command);
		
		if(command.equals("/home.us")) { // 홈 화면으로
			forward = new ActionForward();
			forward.setPath("/index.jsp");
		}else if(command.equals("/login.us")) { // 로그인 화면으로
			forward = new ActionForward();
			forward.setPath("/jsp/user/login.jsp");
		} else if(command.equals("/terms.us")) { // 회원가입 약관
			forward = new ActionForward();
			forward.setPath("/jsp/user/terms.jsp");
		} else if(command.equals("/joinForm.us")) { // 회원가입으로 가기
			forward = new ActionForward();
			forward.setPath("/jsp/user/join.jsp");
		} else if(command.equals("/joinUser.us")) { // 회원가입 처리
			action = new JoinUserAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/sendEmail.us")) { // 회원가입에 필요한 이메일
			action = new SendEmailAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/emailChkPro.us")) { // 회원가입 인증 메일 확인
			action = new EmailChkProAciton();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/loginAction.us")) { // 로그인 하기
			action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/userDetail.us")) { // 사용자 정보가기전 세션값 확인
			action = new UserDetailAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/userDetailAction.us")) { // 사용자 정보로 가기
			forward = new ActionForward();
			forward.setPath("/jsp/user/userDetail.jsp");
		} else if(command.equals("/logout.us")) { // 로그아웃
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/deleteUser.us")) { // 사용자 삭제하러 가기
			forward = new ActionForward();
			forward.setPath("/jsp/user/deleteUserPro.jsp");
		} else if(command.equals("/deleteUserAction.us")) { // 사용자 삭제 처리
			action = new DeleteUserAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/userInfo.us")) { // 사용자 정보로 가기
			action = new UserInfoAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/userInfoModify.us")) { // 사용자 정보 수정
			action = new UserInfoModyfiyAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/findInfo.us")) { // 사용자 아이디 비밀번호 찾기로 가기
			forward = new ActionForward();
			forward.setPath("/jsp/user/findUserInfo.jsp");			
		} else if(command.equals("/findUserInfoAction.us")) { // 사용자 아이디 비밀번호 찾기 처리
			action = new FindUserInfoAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/userPwChange.us")) { // 사용자 비밀번호 변경으로 가기
			forward = new ActionForward();
			forward.setPath("/jsp/user/changeUserPw.jsp");
		} else if(command.equals("/changeUserPwAction.us")) { // 사용자 비밀번호 변경
			action = new ChangeUserPwAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/tetris.us")) {
			forward = new ActionForward();
			forward.setPath("/jsp/user/tetris.jsp");
		}

		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
