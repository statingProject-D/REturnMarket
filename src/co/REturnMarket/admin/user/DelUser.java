package co.REturnMarket.admin.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.user.dao.UserDAO;

public class DelUser implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id = request.getParameter("user_id");
		
		UserDAO userDao = new UserDAO();
		int result =  userDao.deleteUser(user_id);
		
		if(result == 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('계정 삭제에 실패하셨습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		return "userInfo.ad";
	}
}
