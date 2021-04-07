package pdAction;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import pdSvc.JjimListSvc;
import vo.ActionForward;
import vo.JjimBean;

public class JjimListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");
		
		if(user_id == null || user_id == " ") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인이 필요한 항목 입니다.');");
			script.println("location.href='login.us';");
			script.println("</script>");
			script.close();
		}
		
		JjimListSvc jjimListSvc = new JjimListSvc();
		ArrayList<JjimBean> jjimList = jjimListSvc.getJjimList(user_id);
		
		forward = new ActionForward();
		
		session.setAttribute("jjimList", jjimList);
		forward.setPath("jsp/product/jjimList.jsp");
		
		return forward;
	}
}
