package co.REturnMarket.admin.product;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.product.dao.ProductDAO;
import co.REturnMarket.admin.product.vo.ProductVo;

public class ProductInfo implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("id") == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인 이후 이용가능 합니다.');");
			script.println("location.href='login.ad';");
			script.println("</script>");
			script.close();
		}
		
		ProductDAO pdDao = new ProductDAO();
		ArrayList<ProductVo> voList = pdDao.selectProduct();
		
		if(voList == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('상품이 존재하지 않습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		request.setAttribute("page", "/WEB-INF/views/product/pdInfo.jsp");
		request.setAttribute("voList", voList);
		
		return "main/main.jsp";
	}
}
