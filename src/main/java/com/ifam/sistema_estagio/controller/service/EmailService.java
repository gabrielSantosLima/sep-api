package com.ifam.sistema_estagio.controller.service;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import com.ifam.sistema_estagio.model.entity.EmailSimples;

public class EmailService {

	public static void sendEmailAttachment(EmailSimples mensagem, String anexoUrl) throws EmailException {
		
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(anexoUrl);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
//		attachment.setDescription("Picture of John");
//		attachment.setName("John");

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName(mensagem.getHostName());
		email.addTo(mensagem.getTo());
		email.setFrom(mensagem.getFrom());
		email.setSubject(mensagem.getSubject());
		email.setMsg(mensagem.getMessage());

		// add the attachment
		email.attach(attachment);

		// send the email
		email.send();
	}
}
