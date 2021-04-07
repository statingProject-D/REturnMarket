package co.REturnMarket.admin.product;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.product.dao.ProductDAO;
import co.REturnMarket.admin.product.vo.ProductVo;

public class PdView implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pdNum = Integer.parseInt(request.getParameter("pdNum"));
		
		ProductDAO pdDao = new ProductDAO();
		ProductVo vo = pdDao.getProduct(pdNum);
		
		if(vo == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('게시글을 불러오는데 실패했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		request.setAttribute("page", "/WEB-INF/views/product/pdView.jsp");
		request.setAttribute("vo", vo);
		
		return "main/main.jsp";
	}
}
