package boardAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import boardSvc.UploadCmtSvc;
import vo.ActionForward;
import vo.CmtBean;

public class UploadCommnet implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		CmtBean cmtBean = new CmtBean();
		
		int no = Integer.parseInt(request.getParameter("no"));
		String user_id = (String)session.getAttribute("user_id");
		String content = request.getParameter("content");
		
		cmtBean.setCmt_board(no);
		cmtBean.setUser_id(user_id);
		cmtBean.setContent(content);
		
		UploadCmtSvc uploadCmtSvc = new UploadCmtSvc();
		boolean uploadCmtSuccess = uploadCmtSvc.uploadCmt(cmtBean);

		if(!uploadCmtSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('댓글 등록에 실패하셨습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			forward = new ActionForward();
			forward.setPath("qnaView.bo?no=" +no);
		}
		
		return forward;
	}
}
