package com.project.booktime.controllers;

import com.project.booktime.services.MailService;
import com.project.booktime.services.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@Controller
public class MailController {

    @GetMapping("/sendMail")
    public void sendMail() {
        MailServiceImpl mailService = new MailServiceImpl();
        mailService.sendSimpleMail("quentinpeze@hotmail.fr", "test", "test");
    }
}
