package co.REturnMarket.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.common.SHA512SecureUtil;
import co.REturnMarket.admin.dao.AdminDAO;
import co.REturnMarket.admin.vo.AdminVo;

public class LoginAction implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id").trim();
		String pw = request.getParameter("pw").trim();
		String hashpw = SHA512SecureUtil.SHA256(pw);
		
		AdminDAO adminDao = new AdminDAO();
		int result = adminDao.loginAdmin(id, hashpw);
		
		if(result == -1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디가 일치하지 않습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else if(result == 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 일치하지 않습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else if(result == 2) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('관리자 승인을 받지 못한 아이디 입니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else if(result == -2) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('관리자 정보를 가져오는데 실패했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			HttpSession session = request.getSession(); 
			session.setAttribute("id", id);
		}
		
		return "main/main.jsp";
	}
}
