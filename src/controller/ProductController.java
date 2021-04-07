package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import pdAction.AgreeAskAction;
import pdAction.AskDirectBuyAction;
import pdAction.BidPdAction;
import pdAction.DealPdAction;
import pdAction.DealPdInfoAction;
import pdAction.DelJjimAction;
import pdAction.DeletePdAction;
import pdAction.DirectBuyAction;
import pdAction.DirectBuyListAction;
import pdAction.GoUploadAction;
import pdAction.JjimListAction;
import pdAction.JjimPdAction;
import pdAction.ModiPdAction;
import pdAction.MyPdListAction;
import pdAction.ProductListAction;
import pdAction.RejectAskAction;
import pdAction.SearchPdAction;
import pdAction.PdUploadAction;
import pdAction.PdViewAction;
import vo.ActionForward;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("*.pd")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
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
		
		if (command.equals("/uploadPd.pd")) { // 파일 업로드 버튼 / session 여부 확인
			action = new GoUploadAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/addProduct.pd")) { // 업로드로 가기
			forward = new ActionForward();
			forward.setPath("/jsp/product/addProduct.jsp");
		} else if(command.equals("/pdUploadAction.pd")) { // 업로드 처리
			action = new PdUploadAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/productList.pd")) { // 제품 리스트로 바로가기
			action = new ProductListAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/pdView.pd")) { // 제품 화면 가기
			action = new PdViewAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/bidPd.pd")) { // 입찰
			action = new BidPdAction();
			try {
				forward = action.execute(request, response); 
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/searchPd.pd")) { // 검색
			action = new SearchPdAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/jjimPd.pd")) { // 찜하기 추가
			action = new JjimPdAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/jjimList.pd")) { // 찜하기 리스트로 가기
			action = new JjimListAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/delJjim.pd")) { // 찜하기 지우기
			action = new DelJjimAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/directBuy.pd")) { // 즉시구매가로 구매 요청하러 가기
			action = new DirectBuyAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/askDirectBuy.pd")) { // 즉시구매 요청 등록하기
			action = new AskDirectBuyAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/directBuyList.pd")) { // 즉시구매 요청 리스트 보기
			action = new DirectBuyListAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/agreeAsk.pd")) { // 즉시 구매 동의
			action = new AgreeAskAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/rejectAsk.pd")) { // 즉시 구매 거절
			action = new RejectAskAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/modiPd.pd")) { // 글 수정하러 가기
			forward = new ActionForward();
			forward.setPath("/jsp/product/modiPd.jsp");
		} else if(command.equals("/modiPdAction.pd")) {
			action = new ModiPdAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/delPd.pd")) { // 글 삭제하러 가기
			action = new DeletePdAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/myPdList.pd")) { // 입찰 내역보기
			action = new MyPdListAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/dealPdInfo.pd")) { // 낙찰거래 확인 화면으로 가기
			action = new DealPdInfoAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/dealPd.pd")) { // 낙찰 거래
			action = new DealPdAction();
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
