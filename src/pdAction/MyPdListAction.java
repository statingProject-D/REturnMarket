package pdAction;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import pdSvc.MyPdListSvc;
import vo.ActionForward;
import vo.PdBean;

public class MyPdListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String user_id = null;
		
		if(session.getAttribute("user_id") != user_id || session.getAttribute("user_id") != null) {
			user_id =  (String)session.getAttribute("user_id");
		}

		if(user_id == null || user_id == " ") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인이 필요한 항목 입니다.');");
			script.println("location.href='login.us';");
			script.println("</script>");
			script.close();
		} else {
			MyPdListSvc myPdListSvc = new MyPdListSvc();
			ArrayList<PdBean> myPdList = myPdListSvc.getMyPdList(user_id);
		
			forward = new ActionForward();
			
			request.setAttribute("myPdList", myPdList);
			forward.setPath("jsp/product/myPdList.jsp");
		}
		return forward;
	}
}
