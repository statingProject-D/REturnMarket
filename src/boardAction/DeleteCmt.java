package boardAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import boardSvc.DeleteCmtSvc;
import vo.ActionForward;

public class DeleteCmt implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int no = Integer.parseInt(request.getParameter("no"));
		int cmt_no = Integer.parseInt(request.getParameter("cmtNo"));
		
		DeleteCmtSvc deleteCmtSvc = new DeleteCmtSvc();
		boolean deleteSuccess = deleteCmtSvc.deleteComment(cmt_no);
		
		if(!deleteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('삭제 실패하셨습니다.');");
			script.println("<script>");
			script.println("</script>");
			script.close();
		} else {
			forward = new ActionForward();
			forward.setPath("qnaView.bo?no=" +no);
		}
		
		return forward;
	}
}
