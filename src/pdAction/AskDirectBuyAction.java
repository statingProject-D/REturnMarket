package pdAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import pdSvc.AskDirectBuySvc;
import vo.ActionForward;
import vo.AskDirectBean;
import vo.PdBean;

public class AskDirectBuyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		AskDirectBean askDirectBean = new AskDirectBean();
		PdBean pdBean = (PdBean)session.getAttribute("pdBean");
		
		String user_id = (String)session.getAttribute("user_id");
		String askWord = request.getParameter("askWord");
		
		askDirectBean.setPdNum(pdBean.getPdNum());
		askDirectBean.setPdName(pdBean.getPdName());
		askDirectBean.setUploader(pdBean.getUser_id());
		askDirectBean.setUser_id(user_id);
		askDirectBean.setAskWord(askWord);
		
		AskDirectBuySvc askDirectSvc = new AskDirectBuySvc();
		boolean askDirectSuccess = askDirectSvc.addAskDirectBuy(askDirectBean);
		
		if(askDirectSuccess) {
			forward = new ActionForward();
			forward.setPath("pdView.pd?pdNum="+pdBean.getPdNum());
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('요청보내기에 실패했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		return forward;
	}
}
