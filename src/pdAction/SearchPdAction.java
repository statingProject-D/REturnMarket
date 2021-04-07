package pdAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import pdSvc.SearchWordSvc;
import vo.ActionForward;
import vo.PdBean;

public class SearchPdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		SearchWordSvc searchWordSvc = new SearchWordSvc();
		
		String bgCg = request.getParameter("bgCg");
		String smCg = request.getParameter("smCg");
		String searchPdName = request.getParameter("searchPdName");
		
		if(searchPdName.trim() == null || searchPdName.trim() == " ") {
			ArrayList<PdBean> pdList = searchWordSvc.searchWord(bgCg, smCg);
			
			forward = new ActionForward();
			session.setAttribute("pdList", pdList);
			forward.setPath("jsp/product/productList.jsp");
			
		} else if(bgCg == null && smCg == null && searchPdName != null){
			ArrayList<PdBean> pdList = searchWordSvc.pdNameSearch(searchPdName);
			
			forward = new ActionForward();
			session.setAttribute("pdList", pdList);
			forward.setPath("jsp/product/productList.jsp");
		} else {	
			ArrayList<PdBean> pdList = searchWordSvc.totalSearch(bgCg, smCg, searchPdName);
			
			forward = new ActionForward();
			session.setAttribute("pdList", pdList);
			forward.setPath("jsp/product/productList.jsp");
		}	
		
		return forward;
	}
}