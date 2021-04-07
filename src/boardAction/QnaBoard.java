package boardAction;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import boardSvc.QnaListSvc;
import vo.ActionForward;
import vo.PageInfo;
import vo.QnaBean;

public class QnaBoard implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user_id") == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인 이후 볼수 있습니다.');");
			script.println("location.href='login.us'");
			script.println("</script>");
			script.close();
		} else {
			QnaListSvc qnaListSvc = new QnaListSvc();
			
			int page = 1;
			int limit = 10;
			int limitPage = 10; // 글 갯수를 조절하기 위해서
			// page수를 정의 해주기 위해서 / limit는 1page에 보이는 목록(글)의 갯수를 의미한다.
			
			if(request.getParameter("page") != null && !request.getParameter("page").equals("null")) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int listCount = qnaListSvc.getListCount();
			// 총 페이지 수
			int maxPage = (int)((double)listCount / limit + 0.95);
			// 현재 페이지에 보여줄 시작페이지수(1, 11, 21등..)
			int startPage = ((int) ((double)page / limitPage + 0.9) - 1) * limitPage + 1;
			// 현재 페이지에 보여줄 마지막 페이지수 (10,20,30 등..)
			int endPage = startPage + limitPage - 1;
			
			if(endPage > maxPage) endPage = maxPage; //endPage넘어 가는 값을 막아 주기 위해서 
			
			PageInfo pageInfo = new PageInfo();
			pageInfo.setEndPage(endPage);
			pageInfo.setListCount(listCount);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setNowPage(page);
			pageInfo.setStartPage(startPage);
			
			ArrayList<QnaBean> qnaList = qnaListSvc.getQnaList(page, limit);
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("qnaList", qnaList);
			
			forward = new ActionForward();
			forward.setPath("jsp/board/qnaBoard.jsp");
		}
		
		return forward;
	}
}
