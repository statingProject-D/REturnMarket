package pdAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import pdSvc.PdListSvc;
import vo.ActionForward;
import vo.PdBean;

public class ProductListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PdListSvc pdListSvc = new PdListSvc();
		ArrayList<PdBean> pdList = pdListSvc.getPdList();
		
		ActionForward forward = new ActionForward();
		
		request.setAttribute("pdList", pdList);
		forward.setPath("jsp/product/productList.jsp");
		
		return forward;
	}
}
