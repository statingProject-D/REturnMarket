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
		
		if(command.equals("/home.us")) { // ??? ????????????
			forward = new ActionForward();
			forward.setPath("/index.jsp");
		}else if(command.equals("/login.us")) { // ????????? ????????????
			forward = new ActionForward();
			forward.setPath("/jsp/user/login.jsp");
		} else if(command.equals("/terms.us")) { // ???????????? ??????
			forward = new ActionForward();
			forward.setPath("/jsp/user/terms.jsp");
		} else if(command.equals("/joinForm.us")) { // ?????????????????? ??????
			forward = new ActionForward();
			forward.setPath("/jsp/user/join.jsp");
		} else if(command.equals("/joinUser.us")) { // ???????????? ??????
			action = new JoinUserAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/sendEmail.us")) { // ??????????????? ????????? ?????????
			action = new SendEmailAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/emailChkPro.us")) { // ???????????? ?????? ?????? ??????
			action = new EmailChkProAciton();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/loginAction.us")) { // ????????? ??????
			action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/userDetail.us")) { // ????????? ??????????????? ????????? ??????
			action = new UserDetailAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/userDetailAction.us")) { // ????????? ????????? ??????
			forward = new ActionForward();
			forward.setPath("/jsp/user/userDetail.jsp");
		} else if(command.equals("/logout.us")) { // ????????????
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/deleteUser.us")) { // ????????? ???????????? ??????
			forward = new ActionForward();
			forward.setPath("/jsp/user/deleteUserPro.jsp");
		} else if(command.equals("/deleteUserAction.us")) { // ????????? ?????? ??????
			action = new DeleteUserAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/userInfo.us")) { // ????????? ????????? ??????
			action = new UserInfoAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/userInfoModify.us")) { // ????????? ?????? ??????
			action = new UserInfoModyfiyAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/findInfo.us")) { // ????????? ????????? ???????????? ????????? ??????
			forward = new ActionForward();
			forward.setPath("/jsp/user/findUserInfo.jsp");			
		} else if(command.equals("/findUserInfoAction.us")) { // ????????? ????????? ???????????? ?????? ??????
			action = new FindUserInfoAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/userPwChange.us")) { // ????????? ???????????? ???????????? ??????
			forward = new ActionForward();
			forward.setPath("/jsp/user/changeUserPw.jsp");
		} else if(command.equals("/changeUserPwAction.us")) { // ????????? ???????????? ??????
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
