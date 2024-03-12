package com.integrax.service;

import javax.mail.MessagingException;

public interface MailService {
	
	public void sendEmail(String to, String subject, String text) throws MessagingException;

}

