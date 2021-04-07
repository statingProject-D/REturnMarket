package co.REturnMarket.admin.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.common.SHA512SecureUtil;
import co.REturnMarket.admin.user.dao.UserDAO;

public class ChangePw implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id = request.getParameter("user_id");
		String pw = request.getParameter("pw");
		String hashpw = SHA512SecureUtil.SHA256(pw);
		
		UserDAO userDao = new UserDAO();
		int updateSuccess = userDao.updatePw(user_id, hashpw);
		
		if(updateSuccess < 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호 변경에 실패했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		return "userInfo.ad";
	}
}
