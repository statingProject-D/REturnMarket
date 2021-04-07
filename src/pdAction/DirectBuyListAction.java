package pdAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import pdSvc.AskDirectListSvc;
import vo.ActionForward;
import vo.AskDirectBean;
import vo.PdBean;

public class DirectBuyListAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");
		PdBean pdBean = (PdBean)session.getAttribute("pdBean");
		
		AskDirectListSvc askDirectListSvc = new AskDirectListSvc(); 
		ArrayList<AskDirectBean> directList = askDirectListSvc.getDirectList(pdBean, user_id);
		
		forward = new ActionForward();
		session.setAttribute("directList", directList);
		forward.setPath("jsp/product/directList.jsp");
		
		return forward;
	}
}
