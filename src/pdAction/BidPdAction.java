package pdAction;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import pdSvc.BidUploadSvc;
import pdSvc.PdViewSvc;
import userSvc.GetUserInfoSvc;
import vo.ActionForward;
import vo.BidBean;
import vo.PdBean;
import vo.UserBean;

public class BidPdAction implements Action {

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
		}
		
		int pdNum = Integer.parseInt(request.getParameter("pdNum"));
		PdBean pdBean = PdViewSvc.getPdView(pdNum);
		UserBean userBean = GetUserInfoSvc.getUserInfo(user_id);
		
		if(user_id.equals(pdBean.getUser_id())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('본인 물건 입찰은 참아주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			BidBean bidBean = new BidBean();
			bidBean.setPdNum(pdNum);
			bidBean.setUser_id(userBean.getUser_id());
			bidBean.setStartPrice(pdBean.getAuStartPrice());
			bidBean.setUnit(pdBean.getAuUnit());
			
			String chkLastBider = BidUploadSvc.chkLastBider(bidBean);
			
			if(chkLastBider.equals(user_id)) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('마지막 입찰자입니다. 입찰 전으로 돌아갑니다.');");
				script.println("location.href='pdView.pd?pdNum=" +pdBean.getPdNum() +"'");
				script.println("</script>");
				script.close();
			} else {
					
				BidUploadSvc bidUploadSvc = new BidUploadSvc();
				ArrayList<BidBean> bidList = bidUploadSvc.getBidList(bidBean);
				
				boolean isBidUploadSuccess = bidUploadSvc.uploadBid(bidBean);
				
				if(isBidUploadSuccess != false) {
					forward = new ActionForward();
					session.setAttribute("pdBean", pdBean);
					session.setAttribute("userBean", userBean);
					session.setAttribute("bidBean", bidBean);
					session.setAttribute("bidList", bidList);
					
					forward.setPath("pdView.pd?pdNum=" +pdBean.getPdNum());
				} else {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('입찰에 실패하셨습니다.');");
					script.println("history.back();");
					script.println("</script>");
					script.close();
				}
			}
		}
		return forward;
	}
}
