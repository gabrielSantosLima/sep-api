package com.ifam.sistema_estagio.email;

import com.ifam.sistema_estagio.dto.EmailSimplesDto;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class EmailHtmlService implements IEmailService {

    private static final String HTML_VALUE = "text/html";

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Override
    public void enviar(EmailSimplesDto email) throws Exception{
        try{
            val message = emailSender.createMimeMessage();
            val helper = new MimeMessageHelper(message, true);
            val context = new Context();
            context.setVariables(email.getParams());
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            val text = springTemplateEngine.process("email.html", context);
            message.setContent(text, HTML_VALUE);
            emailSender.send(message);
        }catch (Exception e) {
            throw new Exception("Erro ao enviar e-mail");
        }
    }
}
