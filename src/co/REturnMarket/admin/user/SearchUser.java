package co.REturnMarket.admin.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.user.dao.UserDAO;
import co.REturnMarket.admin.user.vo.UserVo;

public class SearchUser implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String search = request.getParameter("search").trim();
		
		UserDAO userDao = new UserDAO();
		ArrayList<UserVo> voList = userDao.searchUserId(search);

		request.setAttribute("page", "/WEB-INF/views/user/userInfo.jsp");
		request.setAttribute("voList", voList);
		
		return "main/main.jsp";
	}
}
