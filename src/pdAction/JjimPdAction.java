package pdAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import pdSvc.JjimAddSvc;
import vo.ActionForward;
import vo.JjimBean;
import vo.PdBean;

public class JjimPdAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		int pdNum = (int)session.getAttribute("pdNum");
		String user_id = (String)session.getAttribute("user_id");
		PdBean pdBean = (PdBean)session.getAttribute("pdBean");
		
		if(user_id.equals(pdBean.getUser_id())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('본인물건은 찜 할 수 없습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		JjimBean jjimBean = new JjimBean();
		jjimBean.setPdNum(pdBean.getPdNum());
		jjimBean.setUser_id(pdBean.getUser_id());
		jjimBean.setPdName(pdBean.getPdName());
		jjimBean.setPdCategory(pdBean.getPdCategory());
		jjimBean.setPdGroup(pdBean.getPdGroup());
		
		JjimAddSvc jjimAddSvc = new JjimAddSvc();
		boolean jjimAddSuccess = jjimAddSvc.addJjims(jjimBean, pdNum, user_id);
		
		if(!jjimAddSuccess) { // 실패
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('중복으로 찜할 수 없습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			forward = new ActionForward();
			session.setAttribute("jjimBean", jjimBean);
			
			forward.setPath("jsp/product/pdView.jsp");
		}
		
		return forward;
	}
}
