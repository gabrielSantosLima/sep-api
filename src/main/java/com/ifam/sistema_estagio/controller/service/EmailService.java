package com.ifam.sistema_estagio.controller.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.EmailSimples;

@Service
public class EmailService {

	public void sendEmailAttachment(EmailSimples emailUser, String anexoUrl) throws EmailException {
		
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(anexoUrl);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName(emailUser.getHostName());
		email.addTo(emailUser.getTo());
		email.setFrom(emailUser.getFrom());
		email.setSubject(emailUser.getSubject());
		email.setMsg(emailUser.getMessage());

		// add the attachment
		email.attach(attachment);

		// send the email
		email.send();
	}
	
	public void sendSimpleEmail(EmailSimples emailUser) throws EmailException {
		
		Email email = new SimpleEmail();
		email.setHostName(emailUser.getHostName());
		email.setSmtpPort(emailUser.getSmtpPort());
		email.setAuthenticator(new DefaultAuthenticator(emailUser.getUsername(), emailUser.getPassword()));
		email.setSSLOnConnect(emailUser.getSslInConnection());
		email.setFrom(emailUser.getFrom());
		email.setSubject(emailUser.getSubject());
		email.setMsg(emailUser.getMessage());
		email.addTo(emailUser.getTo());
		email.send();
	}
}
