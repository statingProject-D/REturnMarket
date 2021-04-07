package co.REturnMarket.admin.product;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.product.dao.ProductDAO;
import co.REturnMarket.admin.product.vo.ProductVo;

public class SearchPd implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String search = request.getParameter("search");
		
		ProductDAO pdDao = new ProductDAO();
		ArrayList<ProductVo> voList = pdDao.searchPdName(search);
		
		request.setAttribute("page", "/WEB-INF/views/product/pdInfo.jsp");
		request.setAttribute("voList", voList);
		
		return "main/main.jsp";
	}
}
