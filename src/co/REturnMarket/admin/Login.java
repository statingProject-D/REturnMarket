package co.REturnMarket.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;

public class Login implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		return "admin/login.jsp";
	}
}
