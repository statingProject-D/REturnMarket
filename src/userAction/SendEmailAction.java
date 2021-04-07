package userAction;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import mail.Gmail;
import security.SHA256;
import userSvc.EmailChkSvc;
import vo.ActionForward;
import vo.UserBean;

public class SendEmailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String session_id = null;
		
		String user_id = request.getParameter("user_id");
		String email = request.getParameter("email");
		
		if(session.getAttribute("user_id") != null) {
			session_id = (String)session.getAttribute("user_id"); 
		}
				
		if(session_id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해주세요');");
			script.println("location.href='login.us';");
			script.println("</script>");
			script.close();
		}
		
		// 이메일 인증 확인
		EmailChkSvc emailChkSvc = new EmailChkSvc(); 
		boolean emailChecked = emailChkSvc.emailchked(user_id);
		
		if(emailChecked == true) { // 이메일 체크 되어있는지 확인 인증이 안되었을경우 아래로
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 인증 된 회원이십니다.');");
			script.println("location.href='home.us';");
			script.println("</script>");
			script.close();
		}
		
		// 메세지 기입
		String host = "http://localhost:8088/REturnMarket/";
		String from = "chsm3285@gamil.com";
		String to = email;
		String subject = "회원 가입 인증을 위한 이메일 확인 입니다.";
		String content = "다음 링크를 클릭 하셔서 이메일 확인을 진행하세요."
				+"<a href='" +host +"jsp/emailChkPro.jsp?code=" +new SHA256().getSHA256(to) +"'>이메일 인증하기</a>";
		
		// SMTP에 접속하기 위한 정보 기입
		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.googlemail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		try {
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html;charset=UTF-8");
			Transport.send(msg);
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이메일 인증 메일을 보내드렸습니다. 확인해주세요^^');");	
			script.println("location.href='login.us';");
			script.println("</script>");

		} catch(Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		return forward;
	}
}
