package co.REturnMarket.admin.main.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.dao.AdminDAO;

public class AgreeAdmin implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		AdminDAO adminDao = new AdminDAO();
		int updateSuccess = adminDao.updateAdmin(id);
		
		if(updateSuccess < 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('승인에 실패했습니다.')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} 
		
		return "main/main.jsp";
	}
}
