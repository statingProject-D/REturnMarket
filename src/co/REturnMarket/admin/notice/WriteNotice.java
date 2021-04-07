package co.REturnMarket.admin.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;

public class WriteNotice implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("page", "/WEB-INF/views/notice/writeNotice.jsp");
		return "main/main.jsp";
	}
}
