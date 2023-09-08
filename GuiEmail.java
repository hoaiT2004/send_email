package util;
//
//import java.util.Date;
//import java.util.Properties;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.eclipse.jdt.internal.compiler.batch.Main;

import javax.activation.DataHandler;
import javax.activation.DataSource;

public class GuiEmail {
	static final String from = "codeptit123@gmail.com";
	static final String password = "wnhpagypqhffhwjc";
	public static boolean guiEmail(String to,String tieuDe,String noiDung) {
//email:Codeptit123@gmail.com	
	//password:wnhpagypqhffhwjc
//Properties:Khai báo các thuộc tính
	Properties props = new Properties(); 
	props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP HOST
	props.put("mail.smtp.port", "587");//TLS Port
	props.put("mail.smtp.auth", "true");//enable authentication(Check có phải đăng nhập hay k)
	props.put("mail.smtp.starttls.enable", "true");//enable STARTTLS
	/*
	 * TLS:587
	 * SSL:465
	 */
	//Create Authenticator ==> Để đăng nhập tài khoản
	Authenticator auth = new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(from,password);
		}
	};
	/*
	  	CC:Cc viết tắt là Carbon Copy, đối tượng nhận được đưa vào mục này sẽ nhận được bản sao của thư.
	   	Về mặt hiển thị và xử lý, mục Cc gần giống với To.Tuy nhiên về mặt ý nghĩa thì Cc vs To khác nhau ở chỗ:
	  	   Đối tượng nhận trong mục Cc chỉ là những đối tượng được đưa vô luồng mail để (nắm bắt thông tin).
		   Đối tượng nhận không cần thiết phải reply (phản hồi) lại.
	 	Bcc:
	 	   Blind Carbon Copy là tên đầy đủ của Bcc,người nhận thư được đưa vô mục này cũng sẽ nhận được thư như mục To vs Cc, 
	 	tuy nhiên họ sẽ không biết được danh sách những người cùng nhận nó chung với mình.
		Khi người nhận thư trong Bcc nhấn reply (phản hồi) và trao đổi với người gửi thì đoạn hội thoại đó qua mail của hai người cũng là độc lập
		,những người khác dù có nằm trong luồng này cũng không thể nhìn thấy được.
		Thông thường, Bcc được áp dụng nếu đối tượng gửi thư không muốn những người nhận biết thông tin lẫn nhau, giúp bảo vệ sự riêng tư cho người nhận vs những nội dung phản hồi trong tương lai (giúp người gửi và người nhận).
	 */
	//Phiên làm việc
	Session session = Session.getInstance(props, auth);
	//Gửi email
	//	final String to ="nguyenxuanhoa8b@gmail.com"; 
		//Tạo 1 tin nhắn
		MimeMessage msg = new MimeMessage(session);
		try {
			//Kiểu nội dung
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			
			//Người gửi
			msg.setFrom(from);
			
			//Người nhận 
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
		
			//Tiêu đề email
			msg.setSubject(tieuDe);
			
			//Quy định ngày gửi
			msg.setSentDate(new Date());
			
			//Quy định email nhận phản hồi
			//msg.setReplyTo(InternetAddress.parse(to, false));
				
			//Nội dung
			msg.setContent(noiDung,"text/HTML; charset=UTF-8");
			
			//Gửi email
			Transport.send(msg);//transport:chuyên chở
			
			//check
			System.out.println("Gửi email thành công");
			return true;
		} catch (Exception e) {
			System.out.println("Gửi email k thành công");
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args) {
		
	}
}
