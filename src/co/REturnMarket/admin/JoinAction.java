package co.REturnMarket.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.common.SHA512SecureUtil;
import co.REturnMarket.admin.dao.AdminDAO;
import co.REturnMarket.admin.vo.AdminVo;

public class JoinAction implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id").trim();
		String pw = request.getParameter("pw").trim();
		String name = request.getParameter("name").trim();
		int phone = Integer.parseInt(request.getParameter("phone").trim());
		String email = request.getParameter("email").trim();
		
		String hashpw = SHA512SecureUtil.SHA256(pw);
		
		AdminVo vo = new AdminVo();
		vo.setId(id);
		vo.setPw(hashpw);
		vo.setName(name);
		vo.setPhone(phone);
		vo.setEmail(email);
		
		AdminDAO adminDao = new AdminDAO();
		int joinSuccess = adminDao.insertUser(vo);
		
		if(joinSuccess < 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원가입에 실패했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		return "admin/login.jsp";
	}
}
