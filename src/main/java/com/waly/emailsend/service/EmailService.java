package com.waly.emailsend.service;

import com.waly.emailsend.dto.EmailDTO;
import com.waly.emailsend.service.Exceptions.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private JavaMailSender emailSender;

    private String subject = "Obrigado por visitar meu portfólio!";
    private String body = "Segue anexo curriculo!";
    private String attachmentFilePath = "C:\\Users\\walys\\Downloads\\curriculo.pdf";

    public void sendEmail(EmailDTO dto) {
        try{
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(emailFrom);
            helper.setTo(dto.getEmail());
            helper.setSubject(subject);
            helper.setText(body);

            // Adicionando o anexo
            FileSystemResource file = new FileSystemResource(new File(attachmentFilePath));
            helper.addAttachment("curriculo.pdf", file);


            emailSender.send(message);
        }
        catch (MailException | MessagingException e){
            throw new EmailException("Failed to send email");
        }
    }
}
