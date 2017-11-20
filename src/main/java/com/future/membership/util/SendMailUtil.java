package com.future.membership.util;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.future.membership.bean.MembershipDto;

public class SendMailUtil {

	// 发送邮件的服务器地址
	private static String host = "smtp.sina.com";
	// 用于给用户发送邮件的邮箱
	private static String from = "superfuturemen@sina.com";
	// 邮箱的用户名
	private static String username = "superfuturemen@sina.com";
	// 邮箱的密码
//	private static String password = "1234qwer";    //第三方授权码  密码是123456qwer   网易邮箱真坑
	private static String password = "123456qwer";    //

	
	public static void send(List<MembershipDto> list){
		for (MembershipDto membershipDto : list) {
			send(membershipDto);
		}
	}
	
	public static void send(MembershipDto membership) {
		try {
			Properties prop = new Properties();
			prop.put("mail.host",host);
			prop.put("mail.transport.protocol", "smtp");
			prop.put("mail.smtp.auth", true);
			Session session = Session.getInstance(prop);
			session.setDebug(true);
			Transport ts = session.getTransport();
			ts.connect(host, username, password);
			Message message = createEmail(session, membership);
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static void send(MembershipSend membership) {
		try {
			Properties prop = new Properties();
			prop.put("mail.host",host);
			prop.put("mail.transport.protocol", "smtp");
			prop.put("mail.smtp.auth", true);
			Session session = Session.getInstance(prop);
			session.setDebug(true);
			Transport ts = session.getTransport();
			ts.connect(host, username, password);
			Message message = createEmail(session, membership);
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Message createEmail(Session session,MembershipDto membership) throws Exception{
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(membership.getEmail()));
		message.setSubject("第一封Java邮件");
		String info = "咱们开会吧   send mail";
		message.setContent(info, "text/html;charset=UTF-8");
		message.saveChanges();
		return message;
	}
	public static Message createEmail(Session session,MembershipSend send) throws Exception{
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(send.getEmail()));
		message.setSubject(send.getSubject());
		String info = send.getInfo();
		message.setContent(info, "text/html;charset=UTF-8");
		message.saveChanges();
		return message;
	}
	
	public static void main(String[] args) {
		MembershipDto membership = new MembershipDto();
		membership.setEmail("979300544@qq.com");
		System.out.println("send start");
		SendMailUtil.send(membership);
		System.out.println("send end");
	}
}
