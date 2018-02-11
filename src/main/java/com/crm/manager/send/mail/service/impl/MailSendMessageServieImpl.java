package com.crm.manager.send.mail.service.impl;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.crm.manager.send.ISendMessageService;
import com.crm.manager.send.mail.dao.IMailTemplateDao;
import com.crm.manager.send.mail.domain.EmailBean;
import com.crm.manager.send.mail.domain.MailTemplate;
import com.crm.manager.send.mail.util.SendMailThread;

/** 
 * 用来发送 mail 的实现类
 * @author chen
 * @since 2008-1-25
 */  
@Service
public class MailSendMessageServieImpl implements ISendMessageService{  
	private static Logger logger = LoggerFactory.getLogger(MailSendMessageServieImpl.class);

	@Autowired  
    private JavaMailSender mailSender; 
	
	@Value("${spring.mail.username}")
    private String sender;
	
	@Autowired
	IMailTemplateDao mailTemplateDao;

	private ExecutorService service =Executors.newCachedThreadPool();
  
	/**
	 * 简单的邮件发送接口
	 * 
	 * @param to
	 * @param subject
	 * @param text
	 * @throws MessagingException 
	 */
	public void send(String templateId, EmailBean email,Map<String, Object> parameterMap) throws MessagingException {
		logger.info("异步发送邮件线程开始");
		MailTemplate mailTemplate = mailTemplateDao.queryMailTemplateByTemplateId(templateId);
		email.setSubject(mailTemplate.getTemplateSubject());
		email.setMessage(mailTemplate.getTemplateContent());
		service.execute(new SendMailThread(mailSender, email, sender, parameterMap));
	}
	
	private void validationParam(String templateId, EmailBean email,Map<String, Object> parameterMap) throws MessagingException{
		if(StringUtils.isBlank(templateId)){
			throw new MessagingException("模板ID不能为空");
		}
		if(StringUtils.isBlank(templateId)){
			throw new MessagingException("模板ID不能为空");
		}
		if(StringUtils.isBlank(templateId)){
			throw new MessagingException("模板ID不能为空");
		}
		if(StringUtils.isBlank(templateId)){
			throw new MessagingException("模板ID不能为空");
		}
		if (email==null) {
			logger.error("邮件发送缺少必须条件: \r[EmailEntity="+email+"]");
			return;
		}
		if(StringUtils.isBlank(sender)){
			logger.error("发件人为空");
			return;
		}
		if(StringUtils.isBlank(email.getEmail())){
			logger.error("收件人为空");
			return;
		}
		
	}
}  
