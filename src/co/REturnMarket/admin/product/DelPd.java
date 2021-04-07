package co.REturnMarket.admin.product;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.product.dao.ProductDAO;

public class DelPd implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pdNum = Integer.parseInt(request.getParameter("pdNum"));
		
		ProductDAO pdDao = new ProductDAO();
		int deleteSuccess = pdDao.deleteProduct(pdNum);
		
		if(deleteSuccess < 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('상품글을 삭제하는데 실패했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		return "main/main.jsp";
	}
}
