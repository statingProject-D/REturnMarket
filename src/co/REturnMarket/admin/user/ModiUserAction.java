package co.REturnMarket.admin.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.user.dao.UserDAO;
import co.REturnMarket.admin.user.vo.UserVo;

public class ModiUserAction implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserVo vo = new UserVo();
		
		// emailChk 여부 확인
		boolean emailChk = false;
		String emailChking = request.getParameter("emailChk");
		
		if(emailChking.equals("true")) {
			emailChk = true;
		} else {
			emailChk = false;
		}
		
		vo.setUser_id(request.getParameter("user_id"));
		vo.setName(request.getParameter("name").trim());
		vo.setPhone(request.getParameter("phone").trim());
		vo.setEmail(request.getParameter("email").trim());
		vo.setGrade(request.getParameter("grade").trim());
		vo.setAddrNum(Integer.parseInt(request.getParameter("addrNum").trim()));
		vo.setAddr1(request.getParameter("addr1"));
		vo.setAddr2(request.getParameter("addr2"));
		vo.setEmailChk(emailChk);

		UserDAO userDao = new UserDAO();
		int updateSuccess = userDao.modiUser(vo);
		
		if(updateSuccess < 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('계정 수정에 실패하셨습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} 
		
		return "userInfo.ad";
	}
}
