package com.project.booktime.services;

import com.project.booktime.params.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailServiceImpl extends MailService
{
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMail(String to, String subject, String content)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Constants.MAIL_USERNAME);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        emailSender.send(message);
    }
}
