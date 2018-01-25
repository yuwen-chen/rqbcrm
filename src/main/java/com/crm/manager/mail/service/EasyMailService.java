package com.crm.manager.mail.service;

import javax.mail.MessagingException;

import com.crm.manager.mail.entity.EmailEntity;

public interface EasyMailService {
	
	/**
	 * 发送简单邮件
	 * @param email
	 */
	public void sendMessage(EmailEntity email);
	
	/**
	 * 发送复杂带附件的邮件
	 * @param email
	 * @throws MessagingException
	 */
	public void sendMimeMessage(EmailEntity email) throws MessagingException;
}
