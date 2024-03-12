package com.integrax.service.impl;


import java.util.Properties;

import javax.mail.MessagingException;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.integrax.service.MailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	      
	    mailSender.setUsername("casanovacristiana@gmail.com");
	    mailSender.setPassword("lcze rsax avxb tdny");
	      
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	      
	    return mailSender;
	}
	
	@Override
	public void sendEmail(String to, String subject, String text) throws MessagingException {

		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("casanovacristiana@gmail.com");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        
        getJavaMailSender().send(message);
	}
}
