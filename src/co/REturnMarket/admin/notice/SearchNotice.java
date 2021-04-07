package co.REturnMarket.admin.notice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.notice.dao.NoticeDAO;
import co.REturnMarket.admin.notice.vo.NoticeVo;

public class SearchNotice implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String search = request.getParameter("search");
		
		NoticeDAO noticeDao = new NoticeDAO();
		ArrayList<NoticeVo> voList = noticeDao.searchNotice(search);
		
		request.setAttribute("voList", voList);
		request.setAttribute("page", "/WEB-INF/views/notice/noticeInfo.jsp");
		
		return "main/main.jsp";
	}
}
