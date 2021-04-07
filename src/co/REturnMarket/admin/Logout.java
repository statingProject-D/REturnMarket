package co.REturnMarket.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.REturnMarket.admin.common.Command;

public class Logout implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "admin/login.jsp";
	}
}
