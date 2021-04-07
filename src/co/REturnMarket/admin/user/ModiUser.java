package co.REturnMarket.admin.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.user.dao.UserDAO;
import co.REturnMarket.admin.user.vo.UserVo;

public class ModiUser implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id = request.getParameter("user_id");
		
		UserDAO userDao = new UserDAO();
		UserVo vo = userDao.getUserInfo(user_id);
		
		if(vo == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('수정할 이용자 정보가 존재하지 않습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		request.setAttribute("vo", vo);
		request.setAttribute("page", "/WEB-INF/views/user/modiUser.jsp");
		
		return "main/main.jsp";
	}
}
