package com.crm.manager.mail.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.crm.manager.mail.entity.EmailEntity;
import com.crm.manager.mail.service.EasyMailService;

/** 
 * 用来发送 mail 的 service, 其中有一个内部类专门用来供线程使用 
 * @author chen
 * @since 2008-1-25
 */  
public class EasyMailServieImpl implements EasyMailService{  
	private static Logger logger = LoggerFactory.getLogger(EasyMailServieImpl.class);

	// 注入MailSender
	private JavaMailSender javaMailSender;

	private ExecutorService service =Executors.newCachedThreadPool();

	// 设置发件人
	private String from;

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void setFrom(String from) {
		this.from = from;
	}
  
	/**
	 * 简单的邮件发送接口
	 * 
	 * @param to
	 * @param subject
	 * @param text
	 */
	public void sendMessage(EmailEntity email) {
		if (null == email) {
			logger.info("something you need to tell here");
			return;
		}
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setTo(email.getTo());
		simpleMailMessage.setSubject(email.getSubject());
		simpleMailMessage.setText(email.getText());
		simpleMailMessage.setFrom(from);

		service.execute(new MailRunner(javaMailSender, simpleMailMessage));
	} 
    
	/**
	 * 发送复杂格式邮件的接口，可以添加附件，图片，等等，但是需要修改这个方法， 如何做到添加附件和图片论坛上有例子了，需要的同学搜一下,
	 * 事实上这里的text参数最好是来自于模板，用模板生成html页面，然后交给javamail去发送， 如何使用模板来生成html见
	 * {@link http://www.iteye.com/topic/71430 }
	 * 
	 * @param to
	 * @param subject
	 * @param text
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException 
	 */
	public void sendMimeMessage(EmailEntity email) throws MessagingException {
		if (null == email) {
			logger.info("something you need to tell here");
			return;
		}
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setTo(email.getTo());
		helper.setFrom(from);
		helper.setSubject(email.getSubject());

		try {
			this.addAttachmentOrImg(helper, email.getAttachment(), true);
			this.addAttachmentOrImg(helper, email.getImg(), false);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 这里的text是html格式的, 可以使用模板引擎来生成html模板, velocity或者freemarker都可以做到
		helper.setText(email.getText(), true);

		service.execute(new MailRunner(javaMailSender,message));
	}  
    
	/**
	 * 添加附件或者是图片
	 * 
	 * @param helper
	 * @param map
	 * @param isAttachment
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException 
	 */
	private void addAttachmentOrImg(MimeMessageHelper helper, Map<String,String > map, boolean isAttachment) throws MessagingException, UnsupportedEncodingException {
		for(Entry<String, String> entry : map.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
				FileSystemResource file = new FileSystemResource(new File(value));
				if (!file.exists())
					continue;
				if (isAttachment) {
					helper.addAttachment(MimeUtility.encodeWord(key), file);
				} else {
					helper.addInline(key, file);
				}
			}
		}
	}
    
	/**
	 * 用来发送邮件的 Runnable, 该类是一个内部类，之所以使用内部类，而没有使用嵌套类（静态内部类）， 是因为内部类可以之间得到 service
	 * 的 javaMailSender 每次发送邮件都会从线程池中取一个线程, 然后进行发邮件操作
	 * 
	 * @author chen
	 */
	private class MailRunner implements Runnable {
		
		JavaMailSender javaMailSender;
		SimpleMailMessage simpleMailMessage;
		MimeMessage mimeMessage;

		/**
		 * 构造简单文本邮件
		 * 
		 * @param simpleMailMessage
		 */
		public MailRunner(JavaMailSender javaMailSender, SimpleMailMessage simpleMailMessage) {
			if (mimeMessage == null) {
				this.simpleMailMessage = simpleMailMessage;
			}
		}

		/**
		 * 构造复杂邮件，可以添加附近，图片，等等
		 * 
		 * @param mimeMessage
		 */
		public MailRunner(JavaMailSender javaMailSender, MimeMessage mimeMessage) {
			if (simpleMailMessage == null) {
				this.mimeMessage = mimeMessage;
			}
		}
      
	    /** 
	     * 该方法将在线程池中的线程中执行 
	     */  
		public void run() {
			try {
				if (simpleMailMessage != null) {
					javaMailSender.send(this.simpleMailMessage);
				} else if (mimeMessage != null) {
					javaMailSender.send(this.mimeMessage);
				}

			} catch (Exception e) {
				logger.error("logger something here", e);
			}
		}
	}  
}  
