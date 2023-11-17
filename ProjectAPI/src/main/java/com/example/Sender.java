package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Sender {
	
	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(String toemail, String subject, String body)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toemail);
		message.setSubject(subject);
		message.setText(body);
		
		mailSender.send(message);
		
		System.out.println("Mail Sent");
	}
}
