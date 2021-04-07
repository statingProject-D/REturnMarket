package pdAction;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import pdSvc.ModiPdSvc;
import vo.ActionForward;
import vo.PdBean;

public class ModiPdAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		PdBean pdBeanSession = (PdBean)session.getAttribute("pdBean");
		int pdNum = pdBeanSession.getPdNum();
		
		ServletContext context = request.getServletContext();
		String realFolder = ""; // 파일 업로드될 서버 상의 물리적인 경로
		String saveFolder = "/upload";
		realFolder = context.getRealPath(saveFolder);
		int size = 10 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, realFolder, size, "UTF-8", new DefaultFileRenamePolicy());
		
		PdBean pdBean = new PdBean();
		pdBean.setPdNum(pdNum);
		pdBean.setPdName(multi.getParameter("pdName"));
		pdBean.setPdCategory(multi.getParameter("pdCategory"));
		pdBean.setPdImg(multi.getFilesystemName("pdImg"));
		pdBean.setPdCount(Integer.parseInt(multi.getParameter("pdCount")));
		pdBean.setPdInfo(multi.getParameter("pdInfo"));
		pdBean.setPdGroup(multi.getParameter("pdGroup"));
		pdBean.setAuStartPrice(Integer.parseInt(multi.getParameter("auStartPrice")));
		pdBean.setAuUnit(Integer.parseInt(multi.getParameter("auUnit")));
		pdBean.setAuEndDay(multi.getParameter("auEndDay"));
		pdBean.setAuDirectPrice(Integer.parseInt(multi.getParameter("auDirectPrice")));
		
		ModiPdSvc modiPdSvc = new ModiPdSvc();
		boolean modiPdSuccess = modiPdSvc.modifyPd(pdBean);
		
		if(!modiPdSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");			
			script.println("alert('수정에 실패 하셨습니다.');");			
			script.println("history.back();");			
			script.println("</script>");			
			script.close();			
		} else {
			forward = new ActionForward();
			session.setAttribute("pdBean", pdBean);
			forward.setPath("pdView.pd?pdNum="+pdNum);
		}
		
		return forward;
	}
}
