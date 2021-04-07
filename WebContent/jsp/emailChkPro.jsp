<%@page import="java.io.PrintWriter"%>
<%@page import="security.*"%>
<%@page import="dao.UserDAO"%>
<%@page import="userSvc.GetEmailSvc, userSvc.SetEmailChkSvc" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>

<%
	request.setCharacterEncoding("UTF-8");

	String session_id = null;

	if (session.getAttribute("user_id") != null ) {
		session_id = (String) session.getAttribute("user_id");
	}
	
	String code = request.getParameter("code");
	
	if (session_id == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요.');");
		script.println("location.href = '../login.us'");
		script.println("</script>");
		script.close();
		return;
	}
	
	GetEmailSvc getEmailSvc = new GetEmailSvc();
	String getEmail = getEmailSvc.getUserEmail(session_id);
	String codeChk = new SHA256().getSHA256(getEmail);
	boolean rightCode = (codeChk.equals(code)) ? true : false;
	
	System.out.println(rightCode);
	
	SetEmailChkSvc setEmailChkSvc = new SetEmailChkSvc();
	boolean setUserEmailChkSuccess = setEmailChkSvc.setUserEmailChk(session_id);
	
	System.out.println(setUserEmailChkSuccess);

	if (rightCode == true) {
		if(setUserEmailChkSuccess == true) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('인증에 성공했습니다.');");
			script.println("location.href = '../home.us'");
			script.println("</script>");
			script.close();
			return;	
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이메일 인증에 실패 하셨습니다.');");
			script.println("location.href='login.us';");
			script.println("</script>");
			script.close();
		}
		
	} else {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 코드입니다.');");
		script.println("location.href = '../login.us'");
		script.println("</script>");
		script.close();
		return;

	}
%>