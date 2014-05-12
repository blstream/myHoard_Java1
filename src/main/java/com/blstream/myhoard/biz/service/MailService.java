package com.blstream.myhoard.biz.service;

import java.util.List;

public interface MailService {
	public String getTitle();
	public void setTitle(String title);
	public String getMessage();
	public void setMessage(String message);
	public List<String> getRecipients();
	public void setRecipients(List<String> recipients);
	public void sendNotification();
}
