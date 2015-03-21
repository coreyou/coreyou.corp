package coreyou.corp;

import java.util.Properties;
import java.security.Security;
import java.security.MessageDigest;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

/**
 * 要下載JavaMail API 和 Java Activation Framework (JAF) 
 * @author coreyou
 *
 */
public class MailSending {
	
	public static void main(String[] args) {
		// 寄出認證信，這裡利用gmail來寄信，要將gmail的安全設定改為較不安全，才能夠成功的將信寄出
		final String SMTP_AUTH_USER = "coreyou.taiwan";
		final String SMTP_AUTH_PWD = "284xl3-4";
		// final String SMTP_HOST_NAME = "smtp.mail.yahoo.com";
		final String SMTP_HOST_NAME = "smtp.gmail.com";
		final String MAIL_HOST_NAME = "@gmail.com";
		// Get a Properties object
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.smtp.host", SMTP_HOST_NAME);
			props.setProperty("mail.transport.protocol", "smtp");
			// props.setProperty("mail.smtp.user", SMTP_AUTH_USER);
			// props.setProperty("mail.smtp.password", SMTP_AUTH_PWD);
			props.put("mail.smtp.starttls.enable", "true");	// gmail
			props.put("mail.smtp.auth", "true");
							
			// Session s = Session.getDefaultInstance(props, null);
			Session mailSession = Session.getInstance(props, 
				new Authenticator(){
				@Override
					public PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
					}
				}
			);
					
			// -- Create a new message --
			Message message = new MimeMessage(mailSession);
					 
			// -- Set the FROM and TO fields --
			message.setFrom(new InternetAddress(SMTP_AUTH_USER + MAIL_HOST_NAME));	// 發送者為coreyou.taiwan@gmail.com
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("corey_ou@yahoo.com.tw"));	// 收信位址為先前form中填的email
			message.setSubject("Corp Coreyou Activate Your Account");	// 信件主旨
			// 處理信件中html內容: 文字部分(textPart)、圖片部分(picturePart)
			// 文字部分(textPart)
			MimeBodyPart textPart = new MimeBodyPart();
			StringBuffer html = new StringBuffer();
			html.append("<h2>註冊信</h2>");
			html.append("這封信是由Corp Coreyou的會員註冊系統所寄出，請點選下面的網址來進行註冊的下一個步驟：");
//			html.append("<p><a href='http://localhost:8080/coreyou.servletAndJSP/registerSystem/registerConfirm.jsp?m=" + 
//				textEmail + "&c=" + activateCode + "'>" + "localhost:8080/registerConfirm.jsp?m=" + textEmail + "&c=" + activateCode + "</a></p>");
			html.append("<p>如果您不曾提出Corp Coreyou的註冊申請，請您直接刪除本信，抱歉造成您的困擾！</p>");
			html.append("<p>如果您無法連結信中網址，請聯絡我們！</p><hr />");
			html.append("<img src='cid:image'/><br>");
			textPart.setContent(html.toString(), "text/html; charset=UTF-8");
			// 圖片部分(picturePart)
//			MimeBodyPart picturePart = new MimeBodyPart();
//			FileDataSource fds = new FileDataSource(application.getRealPath("/") + "registerSystem/corp.jpg");
//			picturePart.setDataHandler(new DataHandler(fds));
//			picturePart.setFileName(fds.getName());
//			picturePart.setHeader("Content-ID", "<image>");

			Multipart emailBody = new MimeMultipart();
			emailBody.addBodyPart(textPart);
//			emailBody.addBodyPart(picturePart);
			message.setContent(emailBody);
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(SMTP_HOST_NAME, SMTP_AUTH_USER, SMTP_AUTH_PWD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
