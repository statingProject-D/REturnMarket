package pdAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import pdSvc.PdViewSvc;
import vo.ActionForward;
import vo.PdBean;

public class DealPdInfoAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int pdNum = Integer.parseInt(request.getParameter("pdNum"));
		
		PdBean pdBean = PdViewSvc.getPdView(pdNum);
		
		forward = new ActionForward();
		request.setAttribute("pdBean", pdBean);
		forward.setPath("jsp/product/dealPdInfo.jsp");
				
		return forward;
	}
}
