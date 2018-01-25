package com.crm.manager.send.mail;

import java.io.File;

import javax.mail.MessagingException;

public interface IEmailSendService {

	public void sendTextMail(String from,String to,String content);

	public void sendHtmlMail(String from,String to,String htmlContent) throws MessagingException;

	public void sendAttachedImageMail(String from,String to) throws MessagingException;

	public void sendAttenddeFileMail(String from,String to,String Content,File filePath,String fileName) throws MessagingException;

}