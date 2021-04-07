package co.REturnMarket.admin.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.main.Main;
import co.REturnMarket.admin.main.command.AgreeAdmin;
import co.REturnMarket.admin.main.command.Approval;
import co.REturnMarket.admin.main.command.RejectAdmin;
import co.REturnMarket.admin.notice.ModiNotice;
import co.REturnMarket.admin.notice.Notice;
import co.REturnMarket.admin.notice.NoticeDel;
import co.REturnMarket.admin.notice.NoticeView;
import co.REturnMarket.admin.notice.SearchNotice;
import co.REturnMarket.admin.notice.WriteNotice;
import co.REturnMarket.admin.notice.WriteNoticeAction;
import co.REturnMarket.admin.product.DelPd;
import co.REturnMarket.admin.product.ModiPd;
import co.REturnMarket.admin.product.PdView;
import co.REturnMarket.admin.product.ProductInfo;
import co.REturnMarket.admin.product.SearchPd;
import co.REturnMarket.admin.user.ChangePw;
import co.REturnMarket.admin.user.DelUser;
import co.REturnMarket.admin.user.ModiUser;
import co.REturnMarket.admin.user.ModiUserAction;
import co.REturnMarket.admin.user.SearchUser;
import co.REturnMarket.admin.user.UserInfo;
import co.REturnMarket.admin.Join;
import co.REturnMarket.admin.JoinAction;
import co.REturnMarket.admin.Login;
import co.REturnMarket.admin.LoginAction;
import co.REturnMarket.admin.Logout;
import co.REturnMarket.admin.common.Command;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.ad", new Main()); // 메인으로
		map.put("/login.ad", new Login()); // 로그인 으로
		map.put("/join.ad", new Join()); // 회원가입란 으로
		map.put("/joinAction.ad", new JoinAction()); // 회원가입 처리
		map.put("/loginAction.ad", new LoginAction()); // 로그인 처리
		map.put("/logout.ad", new Logout()); // 로그아웃
		
		map.put("/approval.ad", new Approval()); // 관리자 가입 승인 처리
		map.put("/agreeAdmin.ad", new AgreeAdmin()); // 관리자 승인 허가
		map.put("/rejectAdmin.ad", new RejectAdmin()); // 관리자 가입 승인처리 거부 및 삭제
		
		map.put("/userInfo.ad", new UserInfo()); // 이용자 관리
		map.put("/searchUser.ad", new SearchUser()); // 이용자 아이디 조회
		map.put("/modiUser.ad", new ModiUser()); // 이용자 수정
		map.put("/modiUserAction.ad", new ModiUserAction()); // 이용자 수정 처리
		map.put("/changePw.ad", new ChangePw()); // 이용자 비밀번호 변경
		map.put("/delUser.ad", new DelUser()); // 이용자 삭제
		
		map.put("/productInfo.ad", new ProductInfo()); // 게시물 관리
		map.put("/searchPd.ad", new SearchPd()); // 상품명 검색
		map.put("/modiPd.ad", new ModiPd()); // 게시물 수정
		map.put("/pdView.ad", new PdView()); // 게시물 보기
		map.put("/delPd.ad", new DelPd()); // 게시물 삭제
		
		map.put("/notice.ad", new Notice()); // 공지사항 관리
		map.put("/writeNoticed.ad", new WriteNotice()); // 공지사항 작성으로 가기
		map.put("/writeNoticeAction.ad", new WriteNoticeAction()); // 공지사항 작성 처리
		map.put("/searchNotice.ad", new SearchNotice()); // 공지사항 검색
		map.put("/noticeView.ad", new NoticeView()); // 공지사항 보기
		map.put("/modiNotice.ad", new ModiNotice()); // 공지사항 수정
		map.put("/noticeDel.ad", new NoticeDel()); // 공지사항 삭제
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		String path = uri.substring(context.length());
		
		System.out.println(path);
		
		Command command = map.get(path); // 실행할 command를 가져온다.
		
		String viewPage = null;
		try {
			viewPage = command.exec(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if((viewPage.endsWith(".jsp"))) {
			viewPage = "/WEB-INF/views/" +viewPage;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
