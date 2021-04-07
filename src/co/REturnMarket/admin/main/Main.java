package co.REturnMarket.admin.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.REturnMarket.admin.common.Command;

public class Main implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id") == null) {
			return "admin/login.jsp";
		} else {
			return "main/main.jsp";
		}
	}
}
