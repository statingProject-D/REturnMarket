package pdAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;
import vo.PdBean;

public class DirectBuyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		PdBean pdBean = (PdBean)session.getAttribute("pdBean");
		String user_id = (String)session.getAttribute("user_id");
		
		if(user_id.equals(pdBean.getUser_id())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('본인에게 요청은 참아주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		forward = new ActionForward();
		session.setAttribute("pdBean", pdBean);
		session.setAttribute("user_id", user_id);
		forward.setPath("jsp/product/askDirectBuy.jsp");
		
		return forward;
	}
}
