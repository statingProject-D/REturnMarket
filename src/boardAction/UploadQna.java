package boardAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import boardSvc.UploadQnaSvc;
import vo.ActionForward;
import vo.QnaBean;

public class UploadQna implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String user_id = (String)session.getAttribute("user_id");
		String content = request.getParameter("content");
		
		QnaBean qnaBean = new QnaBean();
		
		qnaBean.setTitle(title);
		qnaBean.setUser_id(user_id);
		qnaBean.setContent(content);
		
		UploadQnaSvc uploadQnaSvc = new UploadQnaSvc();
		boolean successUpload = uploadQnaSvc.uploadQna(qnaBean);
		
		if(!successUpload) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('업로드에 실패하셨습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			forward = new ActionForward();
			forward.setPath("qnaBoard.bo");
		}
		
		return forward;
	}
}
