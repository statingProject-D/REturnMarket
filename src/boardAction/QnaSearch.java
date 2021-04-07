package boardAction;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import boardSvc.QnaListSvc;
import vo.ActionForward;
import vo.PageInfo;
import vo.QnaBean;

public class QnaSearch implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String searchWord = request.getParameter("searchWord");
		
		QnaListSvc qnaListSvc = new QnaListSvc();
		
		int page = Integer.parseInt(request.getParameter("pageNum"));
		int limit = 10;
		int limitPage = 10; // 글 갯수를 조절하기 위해서
		// page수를 정의 해주기 위해서 / limit는 1page에 보이는 목록(글)의 갯수를 의미한다.
		
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
		
		ArrayList<QnaBean> qnaList = qnaListSvc.getQnaSearchWord(page, limit, searchWord);
		
		System.out.println(qnaList);
		
		if(qnaList == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('검색 결과가 존재하지 않습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			forward = new ActionForward();
			request.setAttribute("qnaList", qnaList);
			request.setAttribute("pageInfo", pageInfo);
			
			forward.setPath("jsp/board/qnaBoard.jsp");
		}
		
		return forward;
	}
}
