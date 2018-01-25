package com.crm.manager.send.mail.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendEmailUtil {
	
	@Autowired
	private JavaMailSenderImpl mailSender;

	public void sendTextMail(String from,String to,String content){

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		//设置收件人（可为多个，to参数需改为数组类型），寄件人
		simpleMailMessage.setTo(to);
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setSubject("Spring Boot Mail 邮件【文本】");//邮件主题
		simpleMailMessage.setText(content);
		//发送邮件
		mailSender.send(simpleMailMessage);
		System.out.println("邮件已发送");

	}

	public void sendHtmlMail(String from,String to,String htmlContent) throws MessagingException{
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessagehelper = new MimeMessageHelper(mimeMessage);
		mimeMessagehelper.setTo(to);
		mimeMessagehelper.setFrom(from);
		mimeMessagehelper.setSubject("Spring Boot Mail 邮件测试【HTML】");
		//启动html
		mimeMessagehelper.setText(htmlContent, true);
		//发送邮件
		mailSender.send(mimeMessage);
		System.out.print("邮件已发送");

	}

	public void sendAttachedImageMail(String from,String to) throws MessagingException{

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		// multipart模式
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setTo(to);
		mimeMessageHelper.setFrom(from);
		mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【图片】");
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append("spring 邮件测试hello!this is spring mail test。");
		// cid为固定写法，imageId指定一个标识
		sb.append("\"cid:imageId\"/>");
		sb.append("");
		mimeMessageHelper.setText(sb.toString(), true);
		// 设置imageId
		FileSystemResource img = new FileSystemResource(new File("/Users/limbo/Desktop/1.jpg"));
		mimeMessageHelper.addInline("imageId",img);
		mailSender.send(mimeMessage);
		System.out.println("邮件已发送");

	}

	public void sendAttenddeFileMail(String from,String to,String Content,File filePath,String fileName) throws MessagingException{

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"utf-8");
		mimeMessageHelper.setTo(to);
		mimeMessageHelper.setFrom(from);
		mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【附件】");
		mimeMessageHelper.setText(Content, true);
		FileSystemResource file = new FileSystemResource(filePath);
		mimeMessageHelper.addAttachment(fileName, file);
		mailSender.send(mimeMessage);
		System.out.println("邮件已发送");
	}
}
