package com.crm.manager.mail.entity;

import java.util.Map;

public class EmailEntity {

	private String to;

	private String subject;

	private String text;

	// 邮件附件
	private Map<String, String> attachment;

	// 图片
	private Map<String, String> img;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, String> getAttachment() {
		return attachment;
	}

	public void setAttachment(Map<String, String> attachment) {
		this.attachment = attachment;
	}

	public Map<String, String> getImg() {
		return img;
	}

	public void setImg(Map<String, String> img) {
		this.img = img;
	}
}
