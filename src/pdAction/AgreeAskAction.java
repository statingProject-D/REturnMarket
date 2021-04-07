package pdAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import pdSvc.DelPdSvc;
import userSvc.GetUserInfoSvc;
import vo.ActionForward;
import vo.UserBean;

public class AgreeAskAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String requester = request.getParameter("requester");
		int pdNum = Integer.parseInt(request.getParameter("pdNum"));
		
		UserBean userBean = GetUserInfoSvc.getUserInfo(requester);
		
		DelPdSvc delPdSvc = new DelPdSvc();
		boolean delPdSuccess = delPdSvc.deletePd(pdNum);
		
		boolean delDirectList = delPdSvc.deleteDirectList(pdNum, requester);
		
		if(!delPdSuccess && !delDirectList) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('즉시구매요청 처리에 문제가 발생했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			forward = new ActionForward();
			session.setAttribute("userBean", userBean);
			forward.setPath("jsp/product/requesterInfo.jsp");
		}
		
		return forward;
	}
}
