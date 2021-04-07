package co.REturnMarket.admin.main.command;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.dao.AdminDAO;
import co.REturnMarket.admin.vo.AdminVo;

public class Approval implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("id") == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인 이후 이용가능 합니다.');");
			script.println("location.href='login.ad';");
			script.println("</script>");
			script.close();
		}
		
		ArrayList<AdminVo> voList = new ArrayList<AdminVo>();
		AdminDAO adminDao = new AdminDAO();
		
		voList = adminDao.getAdmins();
		
		request.setAttribute("voList", voList);
		request.setAttribute("page", "/WEB-INF/views/admin/approval.jsp");
		
		return "main/main.jsp";
	}
}
