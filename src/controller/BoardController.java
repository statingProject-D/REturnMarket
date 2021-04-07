package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import boardAction.DelQna;
import boardAction.DeleteCmt;
import boardAction.ModiQna;
import boardAction.ModiQnaAction;
import boardAction.NoticeAction;
import boardAction.NoticeSearch;
import boardAction.NoticeView;
import boardAction.QnaBoard;
import boardAction.QnaSearch;
import boardAction.QnaView;
import boardAction.UploadCommnet;
import boardAction.UploadQna;
import vo.ActionForward;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.bo")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
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
		
		if(command.equals("/noticeBoard.bo")) { // 공지사항으로 가기
			action = new NoticeAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/noticeSearch.bo")) { // 공지 검색
			action = new NoticeSearch();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/noticeBoardResult.bo")) { // 공지 결과를 공지사항 홈으로 넘김
			forward = new ActionForward();
			forward.setPath("/jsp/board/noticeBoard.jsp");
		} else if(command.equals("/noticeView.bo")) { // 공지사항 글보기
			action = new NoticeView();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/qnaBoard.bo")) { // qna로 가기
			action = new QnaBoard();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/qnaView.bo")) { // qna 글보기
			action = new QnaView();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/writeQna.bo")) { // 글쓰기로 가기
			forward = new ActionForward();
			forward.setPath("/jsp/board/writeQna.jsp");
		} else if(command.equals("/uploadQna.bo")) { // 글쓰기 업로드
			action = new UploadQna();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/uploadComment.bo")) { // 댓글 업로드
			action = new UploadCommnet();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/modiQna.bo")) { // qna 수정
			action = new ModiQna();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/modiQnaAction.bo")) { // qna 수정
			action = new ModiQnaAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/delQna.bo")) { // qna 지우기
			action = new DelQna();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/qnaSearch.bo")) { // qna 검색 결과 보기
			action = new QnaSearch();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/deletCmt.bo")) { // 댓글 삭제
			action = new DeleteCmt();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
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
