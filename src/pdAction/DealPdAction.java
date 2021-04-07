package pdAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import pdSvc.DelPdSvc;
import pdSvc.GetBuyerInfoSvc;
import vo.ActionForward;
import vo.BidBean;
import vo.UserBean;

public class DealPdAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		int pdNum = Integer.parseInt(request.getParameter("pdNum"));
		
		GetBuyerInfoSvc getBuyerInfoSvc = new GetBuyerInfoSvc(); 
		
		BidBean bidBean = getBuyerInfoSvc.getBuyerBidInfo(pdNum);
		UserBean userBean = getBuyerInfoSvc.getBuyerUserInfo(bidBean);
		
		DelPdSvc delPdSvc = new DelPdSvc();
		boolean deletePd = delPdSvc.deletePd(pdNum);
		
		if(bidBean != null && userBean != null) {
			if(deletePd) {
			forward = new ActionForward();
			session.setAttribute("bidBean", bidBean);
			session.setAttribute("userBean", userBean);
			
			forward.setPath("jsp/product/dealPd.jsp");
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('상품처리에 문제가 발생했습니다.')");
				script.println("history.back();");
				script.println("</script>");
				script.close();
			}
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입찰자가 존재하지 않거나, 구매자의 정보를 불러오는데 실패했습니다.')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		return forward;
	}
}
