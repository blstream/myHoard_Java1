package com.blstream.myhoard.biz.service;

import java.util.List;

public interface MailService {
	public String getHost();
	public void setHost(String host);
	public String getPort();
	public void setPort(String port);
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
	public String getTitle();
	public void setTitle(String title);
	public String getMessage();
	public void setMessage(String message);
	public List<String> getRecipients();
	public void setRecipients(List<String> recipients);
	public void sendMessage();
}
